package com.dss.review.service;


import com.dss.review.exception.MovieNotFoundException;
import com.dss.review.model.Movie;
import com.dss.review.model.Review;
import com.dss.review.model.ReviewDto;
import com.dss.review.proxy.MovieProxy;
import com.dss.review.repository.MovieDao;
import com.dss.review.repository.ReviewDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
public class ReviewServiceTest {

    @MockBean private ReviewDao reviewDao;
    @MockBean private MovieDao movieDao;

    @Autowired private ReviewService reviewService;

    @Test
    public void Saving_review_with_valid_request_and_valid_movie_is_valid(){
        Movie movie = new Movie();
        movie.setId("4028c4ec84419c76018441a921e80001");

        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setMovie(movie);

        Mockito.when(movieDao.findById(movie.getId())).thenReturn(Optional.of(new Movie()));
        Assertions.assertTrue(reviewService.save(reviewDto));
    }

    @Test
    void Saving_review_with_empty_request_is_invalid() {
        ReviewDto reviewDto = null;
        Assertions.assertFalse(reviewService.save(reviewDto));
    }

    @Test
    void Saving_review_with_valid_request_and_empty_movie_is_invalid() {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setMovie(null);
        Assertions.assertFalse(reviewService.save(reviewDto));
    }

    @Test
    public void Finding_review_with_valid_movie_is_valid(){
        Movie movie = new Movie();
        movie.setId("4028c4ec84419c76018441a921e80001");

        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setMovie(movie);

        String expectedMessage = "Movie ID does not exist";
        Exception exception = Assertions.assertThrows(MovieNotFoundException.class, () -> {
            Mockito.when(reviewService.findByMovieId(reviewDto.getMovie().getId())).thenReturn(new Review());
        });
        Assertions.assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void Finding_review_with_invalid_movie_is_invalid(){
        String expectedMessage = "Movie ID does not exist";
        Exception exception = Assertions.assertThrows(MovieNotFoundException.class, () -> {
            Mockito.when(reviewService.findByMovieId(null)).thenReturn(new Review());
        });
        Assertions.assertTrue(exception.getMessage().contains(expectedMessage));
    }

}
