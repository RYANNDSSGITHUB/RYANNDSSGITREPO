package com.dss.service;

import com.dss.exception.CustomErrorException;
import com.dss.model.Review;
import com.dss.model.ReviewDto;

public interface ReviewService extends BaseService<Review> {
    public Review findByMovieId(String id) throws CustomErrorException;
}
