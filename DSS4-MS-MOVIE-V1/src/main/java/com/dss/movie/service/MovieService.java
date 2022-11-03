package com.dss.movie.service;

import com.dss.movie.model.Movie;

import java.util.List;

public interface MovieService {
    public Movie findById(String id);
    public List<Movie> findAll();
    public String save(Movie actor);
    public boolean update(Movie actor);
    public boolean deleteById(String id);
}
