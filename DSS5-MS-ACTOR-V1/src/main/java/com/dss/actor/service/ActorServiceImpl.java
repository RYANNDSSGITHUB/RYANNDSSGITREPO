package com.dss.actor.service;

import com.dss.actor.exception.AbstractRuntimeException;
import com.dss.actor.exception.ActorNotFoundException;
import com.dss.actor.exception.LinkedEntityException;
import com.dss.actor.exception.MovieNotFoundException;
import com.dss.actor.model.Actor;
import com.dss.actor.model.Movie;
import com.dss.actor.proxy.MovieProxy;
import com.dss.actor.repository.ActorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired ActorDao actorDao;
    @Autowired MovieProxy movieProxy;

    private void validateMovieList(Actor actor) throws AbstractRuntimeException {
        if(actor.getMovieList().size() > 0){
            Map<String, Movie> actorMap = movieProxy.findAll().stream()
                    .collect(Collectors.toMap(Movie::getId, movie -> movie));

            for(Movie movie: actor.getMovieList()){
                if(actorMap.put(movie.getId(), movie) == null){
                    throw new MovieNotFoundException("Movie ID does not exist");
                }
            }
        } else {
            throw new MovieNotFoundException("Movie list cannot be null");
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
    public boolean update(String id, Actor newModel) {
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
            throw new ActorNotFoundException("Actor ID does not exist");
        }
        return isSuccess;
    }

    @Override
    public boolean deleteById(String id) {
        Boolean isSuccess = true;

        Optional<Actor> actor = actorDao.findById(id);
        if(actor.isPresent()){
            if(!(actor.get().getMovieList()!=null && actor.get().getMovieList().size()>0)){
                actorDao.deleteById(id);
            } else {
                throw new LinkedEntityException("Cannot delete actor linked to movie");
            }
        }
        return isSuccess;
    }
}
