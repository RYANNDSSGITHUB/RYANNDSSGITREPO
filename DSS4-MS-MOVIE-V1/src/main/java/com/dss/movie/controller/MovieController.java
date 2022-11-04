package com.dss.movie.controller;

import com.dss.movie.exception.AbstractRuntimeException;
import com.dss.movie.model.Movie;
import com.dss.movie.model.MovieRequestModel;
import com.dss.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dss")
public class MovieController {

    @Autowired MovieService movieService;

    @GetMapping("/movie")
    public List<Movie> findAll(){
        return movieService.findAll();
    }

    @GetMapping("/movie/{id}")
    public Movie findById(@PathVariable(name="id") String id){
        return movieService.findById(id);
    }

    @PostMapping("/movie")
    public String add(@RequestBody Movie model){
        return movieService.save(model);
    }

    @PutMapping("/movie/{id}")
    public boolean update(@PathVariable(name="id") String id,
                          @RequestBody MovieRequestModel model)
            throws AbstractRuntimeException {
        return movieService.update(id, model);
    }

    @DeleteMapping("/movie/{id}")
    public boolean deleteById(@PathVariable(name="id") String id)
            throws AbstractRuntimeException {
        return movieService.deleteById(id);
    }
}
