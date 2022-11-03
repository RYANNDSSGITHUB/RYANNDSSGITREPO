package com.dss.movie.controller;

import com.dss.movie.model.Movie;
import com.dss.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dss")
public class MovieController {

    @Autowired MovieService movieService;

    @PostMapping("/movie")
    @ResponseStatus(HttpStatus.OK)
    public void add(@RequestBody Movie model){
        movieService.save(model);
    }
}
