package com.dss.service;

import com.dss.exception.CustomErrorException;
import com.dss.model.Actor;
import com.dss.model.Movie;
import com.dss.repository.ActorDao;
import com.dss.repository.MovieDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired ActorDao actorDao;
    @Autowired MovieDao movieDao;

    private void validateMovieList(Actor actor) throws CustomErrorException {
        if(actor.getMovieList().size() > 0){
            Map<String, Movie> actorMap = movieDao.findAll().stream()
                    .collect(Collectors.toMap(Movie::getId, movie -> movie));

            for(Movie movie: actor.getMovieList()){
                if(actorMap.put(movie.getId(), movie) == null){
                    throw new CustomErrorException("Movie ID does not exist");
                }
            }
        } else {
            throw new CustomErrorException("Movie list cannot be null");
        }
    }

    @Override
    public Actor findById(String id) {
        Optional<Actor> actor = actorDao.findById(id);
        if(actor.isPresent()){
            return actor.get();
        }
        return null;
    }

    @Override
    public List<Actor> findAll() {
        return actorDao.findAll();
    }

    @Override
    public String save(Actor actor) {
        actor = actorDao.save(actor);
        return actor.getId();
    }

    @Override
    public boolean update(String id, Actor newModel) throws CustomErrorException {
        Boolean isSuccess = true;

        Optional<Actor> temp = actorDao.findById(id);
        if(temp.isPresent()){
            Actor oldModel = temp.get();
            oldModel.setFirstName(newModel.getFirstName()!=null ? newModel.getFirstName() : oldModel.getFirstName());
            oldModel.setLastName(newModel.getLastName()!=null ? newModel.getLastName() : oldModel.getLastName());
            oldModel.setGender(newModel.getGender()!=null ? newModel.getGender() : oldModel.getGender());
            oldModel.setAge(newModel.getAge()!=null ? newModel.getAge() : oldModel.getAge());

            if(newModel.getMovieList()!=null){
                validateMovieList(newModel);
                oldModel.setMovieList(newModel.getMovieList());
            }
            actorDao.save(oldModel);
        } else {
            isSuccess = false;
            throw new CustomErrorException("Actor ID does not exist");
        }
        return isSuccess;
    }

    @Override
    public boolean deleteById(String id) throws CustomErrorException {
        Boolean isSuccess = true;

        Optional<Actor> actor = actorDao.findById(id);
        if(actor.isPresent()){
            if(!(actor.get().getMovieList()!=null && actor.get().getMovieList().size()>0)){
                actorDao.deleteById(id);
            } else {
                throw new CustomErrorException("Cannot delete actor linked to movie");
            }
        }
        return isSuccess;
    }
}
