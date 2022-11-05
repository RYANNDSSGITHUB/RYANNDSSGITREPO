package com.dss.service;

import com.dss.exception.CustomErrorException;
import com.dss.model.Movie;
import com.dss.model.MovieDto;

import java.util.List;

public interface MovieService {
    public Movie findById(String id) throws CustomErrorException;
    public List<Movie> findAll();
    public String save(Movie actor) throws CustomErrorException;
    public boolean update(String id, MovieDto oldModel) throws CustomErrorException;
    public boolean deleteById(String id) throws CustomErrorException;
}
