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
public class ReviewServiceImpl extends BaseServiceImpl<Review> implements ReviewService {

    @Autowired ReviewDao reviewDao;
    @Autowired MovieDao movieDao;

    private void validateMovie(Movie movie) {
        try {
            if(movie.getId()!=null){
                Optional<Movie> temp = movieDao.findById(movie.getId());
                if(!temp.isPresent()){
                    throw new CustomErrorException("Movie ID does not exist");
                }
            }
        } catch(CustomErrorException e){
            e.printStackTrace();
        }
    }

    @Override
    public Review findByMovieId(String id) throws CustomErrorException {
        return reviewDao.findByMovieId(id).get();
    }
}
