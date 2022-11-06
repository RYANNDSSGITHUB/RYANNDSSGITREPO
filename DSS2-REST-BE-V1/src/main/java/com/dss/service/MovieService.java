package com.dss.service;

import com.dss.exception.CustomErrorException;
import com.dss.model.Movie;
import com.dss.model.MovieDto;

import java.util.List;

public interface MovieService extends BaseService<Movie> {
    public boolean update(String id, MovieDto oldModel) throws CustomErrorException;
}
