package com.dss.movie.service;

import com.dss.movie.model.Movie;
import com.dss.movie.repository.MovieDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MovieServiceImpl implements MovieService {

    @Autowired MovieDao movieDao;

    @Override
    public void deleteById(String id) {
        movieDao.deleteById(id);
    }

    @Override
    public Movie save(Movie movie) {
        return movieDao.save(movie);
    }

    @Override
    public List<Movie> findAll() {
        return movieDao.findAll();
    }
}
