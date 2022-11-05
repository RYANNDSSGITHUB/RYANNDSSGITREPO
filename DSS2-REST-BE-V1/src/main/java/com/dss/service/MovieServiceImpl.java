package com.dss.service;

import com.dss.exception.CustomErrorException;
import com.dss.model.Actor;
import com.dss.model.Movie;
import com.dss.model.MovieDto;
import com.dss.repository.ActorDao;
import com.dss.repository.MovieDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired MovieDao movieDao;
    @Autowired ActorDao actorDao;

    private void validateActorList(Movie movie) throws CustomErrorException  {
        if(movie.getActorList().size() > 0){
            Map<String, Actor> actorMap = actorDao.findAll().stream()
                    .collect(Collectors.toMap(Actor::getId, actor -> actor));

            for(Actor actor: movie.getActorList()){
                if(actorMap.put(actor.getId(), actor) == null){
                    throw new CustomErrorException("Actor ID does not exist");
                }
            }
        }
    }

    @Override
    public Movie findById(String id) throws CustomErrorException {
        Optional<Movie> movie = movieDao.findById(id);
        if(movie.isPresent()){
            return movie.get();
        }
        return null;
    }

    @Override
    public List<Movie> findAll() {
        return movieDao.findAll();
    }

    @Override
    public String save(Movie movie) throws CustomErrorException {
        if(movie.getActorList() != null){
            validateActorList(movie);
        }
        return movieDao.save(movie).getId();
    }

    @Override
    public boolean update(String id, MovieDto newModel) throws CustomErrorException {
        Boolean isSuccess = true;

        Optional<Movie> temp = movieDao.findById(id);
        if(temp.isPresent()){
            Movie oldModel = temp.get();
            oldModel.setProductionCost(newModel.getProductionCost()!=null ? newModel.getProductionCost() : oldModel.getProductionCost());
            oldModel.setImageDirectory(newModel.getImageDirectory()!=null ? newModel.getImageDirectory() : oldModel.getImageDirectory());
            movieDao.save(oldModel);
        } else {
            isSuccess = false;
            throw new CustomErrorException("Movie ID does not exist");
        }
        return isSuccess;
    }

    @Override
    public boolean deleteById(String id) throws CustomErrorException {
        Boolean isSuccess = true;

        Optional<Movie> movie = movieDao.findById(id);
        if(movie.isPresent()){
            int dateNow = LocalDate.now().getYear();
            int datePast = Integer.parseInt(movie.get().getYearReleased());
            if(dateNow - datePast > 1){
                movieDao.deleteById(id);
            } else {
                throw new CustomErrorException("Unable to delete movie released < 1 year");
            }
        } else {
            isSuccess = false;
            throw new CustomErrorException("Movie ID does not exist");
        }
        return isSuccess;
    }
}
