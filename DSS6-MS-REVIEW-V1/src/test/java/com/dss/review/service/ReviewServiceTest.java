package com.dss.review.service;


import com.dss.review.model.Movie;
import com.dss.review.model.Review;
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

    Movie movie = new Movie("4028c4ec84419c76018441a921e80001", null, null, null,
            null, null, null);

    Review review = new Review("4028c4ec8441a70b018441ad5ced0000", null, null, null, null);

    @Test
    void saveReviewSuccessful() {
        Mockito.when(movieDao.findById(movie.getId())).thenReturn(Optional.of(new Movie()));
        Assertions.assertTrue(reviewService.save(review));
    }

    @Test
    public void findReviewByMovieNotNull(){
        Mockito.when(movieDao.findById(movie.getId())).thenReturn(Optional.of(new Movie()));
        Mockito.when(reviewDao.findByMovieId(movie.getId())).thenReturn(Optional.of(review));
        Assertions.assertNotNull(reviewDao.findByMovieId(movie.getId()));
    }

}
