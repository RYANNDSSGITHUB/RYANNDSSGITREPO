package com.dss.movie.service;

import com.dss.movie.model.Movie;
import com.dss.movie.repository.MovieDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired MovieDao movieDao;

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
        movie = movieDao.save(movie);
        return movie.getId();
    }

    @Override
    public boolean update(Movie movie) {
        Boolean isSuccess = true;
        try {
            if(movie.getId()==null){
                throw new Exception();
            } else {
                movieDao.save(movie);
            }
        } catch(Exception e){
            isSuccess = false;
            e.printStackTrace();
        }
        return isSuccess;
    }

    @Override
    public boolean deleteById(String id) {
        Boolean isSuccess = true;
        try {
            movieDao.deleteById(id);
        } catch (Exception e){
            isSuccess = false;
        }
        return isSuccess;
    }
}
