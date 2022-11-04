package com.dss.review.service;

import com.dss.review.model.Review;

public interface ReviewService {
    public Review findByMovieId(String id);
    public String save(Review review);
}
