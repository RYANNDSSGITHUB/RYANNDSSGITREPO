package com.dss.service;

import com.dss.exception.CustomErrorException;
import com.dss.model.Movie;
import com.dss.model.Review;
import com.dss.model.ReviewDto;
import com.dss.repository.MovieDao;
import com.dss.repository.ReviewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired ReviewDao reviewDao;
    @Autowired MovieDao movieDao;

    private void validateMovie(Movie movie) throws CustomErrorException {
        if(movie.getId()!=null){
            Optional<Movie> temp = movieDao.findById(movie.getId());
            if(!temp.isPresent()){
                throw new CustomErrorException("Movie ID does not exist");
            }
        }
    }

    private Review transformModel(ReviewDto reviewDto) {
        Review review = new Review();
        review.setMessage(reviewDto.getMessage());
        review.setPostedDt(reviewDto.getPostedDt());
        review.setRating(reviewDto.getRating());
        review.setMovie(reviewDto.getMovie());
        return review;
    }

    @Override
    public Review findByMovieId(String id) throws CustomErrorException {
        Optional<Review> review = reviewDao.findByMovieId(id);
        if(review.isPresent()){
            return review.get();
        } else {
            throw new CustomErrorException("Movie ID does not exist");
        }
    }

    @Override
    public boolean save(ReviewDto reviewDto) throws CustomErrorException {
        if(reviewDto!=null && reviewDto.getMovie() != null){
            validateMovie(reviewDto.getMovie());
            reviewDao.save(transformModel(reviewDto));
            return true;
        } else {
            return false;
        }
    }

}
