package com.dss.movie.service;

import com.dss.movie.exception.AbstractRuntimeException;
import com.dss.movie.exception.ActorNotFoundException;
import com.dss.movie.exception.InvalidMovieException;
import com.dss.movie.exception.MovieNotFoundException;
import com.dss.movie.model.Actor;
import com.dss.movie.model.Movie;
import com.dss.movie.model.MovieRequestModel;
import com.dss.movie.proxy.ActorProxy;
import com.dss.movie.repository.MovieDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired MovieDao movieDao;
    @Autowired ActorProxy actorProxy;

    private void validateActorList(Movie movie) throws AbstractRuntimeException  {
        if(!movie.getActorList().isEmpty()){
            Map<String, Actor> actorMap = actorProxy.findAll().stream()
                    .collect(Collectors.toMap(Actor::getId, actor -> actor));

            for(Actor actor: movie.getActorList()){
                if(actorMap.put(actor.getId(), actor) == null){
                    throw new ActorNotFoundException("Actor ID does not exist");
                }
            }
        }
    }

    @Override
    public Movie findById(String id) {
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
    public String save(Movie movie) {
        if(movie.getActorList() != null){
            validateActorList(movie);
        }
        return movieDao.save(movie).getId();
    }

    @Override
    public boolean update(String id, MovieRequestModel newModel) {
        Boolean isSuccess = true;

        Optional<Movie> temp = movieDao.findById(id);
        if(temp.isPresent()){
            Movie oldModel = temp.get();
            if(newModel!=null){
                oldModel.setProductionCost(newModel.getProductionCost()!=null ? newModel.getProductionCost() : oldModel.getProductionCost());
                oldModel.setImageDirectory(newModel.getImageDirectory()!=null ? newModel.getImageDirectory() : oldModel.getImageDirectory());
            }
           movieDao.save(oldModel);
        } else {
            throw new MovieNotFoundException("Movie ID does not exist");
        }
        return isSuccess;
    }

    @Override
    public boolean deleteById(String id) {
        Boolean isSuccess = true;

        Optional<Movie> movie = movieDao.findById(id);
        if(movie.isPresent()){
            int dateNow = LocalDate.now().getYear();
            int datePast = Integer.parseInt(movie.get().getYearReleased());
            if(dateNow - datePast > 1){
                movieDao.deleteById(id);
            } else {
                throw new InvalidMovieException("Unable to delete movie released < 1 year");
            }
        } else {
            throw new MovieNotFoundException("Movie ID does not exist");
        }
        return isSuccess;
    }
}
