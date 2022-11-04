package com.dss.review.service;

import com.dss.review.model.Review;
import com.dss.review.repository.ReviewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired ReviewDao reviewDao;

    @Override
    public Review findByMovieId(String id) {
        Optional<Review> review = reviewDao.findByMovieId(id);
        if(review.isPresent()){
            return review.get();
        }
        return null;
    }

    @Override
    public String save(Review review) {
        if(review.getMovie() != null){
//            validateActorList(movie);
        }
        return reviewDao.save(review).getId();
    }

}
