package com.dss.review.service;

import com.dss.review.exception.MovieNotFoundException;
import com.dss.review.model.Movie;
import com.dss.review.model.Review;
import com.dss.review.proxy.MovieProxy;
import com.dss.review.repository.ReviewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired ReviewDao reviewDao;
    @Autowired MovieProxy movieProxy;

    private void validateMovie(Movie movie){
        if(movie.getId()!=null){
            Optional<Movie> temp = movieProxy.findById(movie.getId());
            if(!temp.isPresent()){
                throw new MovieNotFoundException("Movie ID does not exist");
            }
        }
    }

    @Override
    public Review findByMovieId(String id) {
        Optional<Review> review = reviewDao.findByMovieId(id);
        if(review.isPresent()){
            return review.get();
        } else {
            throw new MovieNotFoundException("Movie ID does not exist");
        }
    }

    @Override
    public boolean save(Review review) {
        if(review!=null && review.getMovie() != null){
            validateMovie(review.getMovie());
        }
        reviewDao.save(review);
        return true ;
    }

}
