package com.dss.review.controller;

import com.dss.review.exception.AbstractRuntimeException;
import com.dss.review.model.Review;
import com.dss.review.model.ReviewDto;
import com.dss.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dss")
public class ReviewController {

    @Autowired ReviewService reviewService;

    @GetMapping("/review/{movieId}")
    public Review findByMovieId(@PathVariable(name="movieId") String movieId)
            throws AbstractRuntimeException {
        return reviewService.findByMovieId(movieId);
    }

    @PostMapping("/review")
    public boolean add(@RequestBody ReviewDto model)
            throws AbstractRuntimeException {
        return reviewService.save(model);
    }
}
