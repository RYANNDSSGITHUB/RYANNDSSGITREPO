package com.dss.service;

import com.dss.exception.CustomErrorException;
import com.dss.model.Review;
import com.dss.model.ReviewDto;

public interface ReviewService {
    public Review findByMovieId(String id) throws CustomErrorException;
    public boolean save(ReviewDto reviewDto) throws CustomErrorException;
}
