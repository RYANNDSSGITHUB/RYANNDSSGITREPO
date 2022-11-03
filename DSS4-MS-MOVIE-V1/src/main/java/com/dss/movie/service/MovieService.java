package com.dss.movie.service;

import com.dss.movie.model.Movie;

import java.util.List;

public interface MovieService {
    public void deleteById(String id);
    public Movie save(Movie movie);
    public List<Movie> findAll();
}
