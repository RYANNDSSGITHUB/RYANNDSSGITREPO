package com.dss.controller;

import com.dss.exception.CustomErrorException;
import com.dss.model.Review;
import com.dss.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dss")
public class ReviewController {

    @Autowired ReviewService reviewService;

    @GetMapping("/review/{movieId}")
    public Review findByMovieId(@PathVariable(name="movieId") String movieId)
            throws CustomErrorException {
        return reviewService.findByMovieId(movieId);
    }

    @PostMapping("/review")
    public Review add(@RequestBody Review model) {
        return reviewService.save(model);
    }
}
