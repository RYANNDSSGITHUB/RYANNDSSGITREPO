package com.dss.movie.service;

import com.dss.movie.model.Movie;
import com.dss.movie.model.MovieDto;

import java.util.List;

public interface MovieService {
    public Movie findById(String id);
    public List<Movie> findAll();
    public String save(Movie actor);
    public boolean update(String id, MovieDto oldModel);
    public boolean deleteById(String id);
}
