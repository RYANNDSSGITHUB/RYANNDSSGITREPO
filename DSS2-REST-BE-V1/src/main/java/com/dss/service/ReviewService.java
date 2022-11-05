package com.dss.review.service;

import com.dss.review.model.Review;
import com.dss.review.model.ReviewDto;

public interface ReviewService {
    public Review findByMovieId(String id);
    public boolean save(ReviewDto reviewDto);
}
