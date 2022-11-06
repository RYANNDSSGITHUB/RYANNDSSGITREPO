package com.dss.controller;

import com.dss.exception.CustomErrorException;
import com.dss.model.Movie;
import com.dss.model.MovieDto;
import com.dss.service.MovieService;
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
    public Movie findById(@PathVariable(name="id") String id) {
        return movieService.findById(id).get();
    }

    @PostMapping("/movie")
    public Movie add(@RequestBody Movie model) {
        return movieService.save(model);
    }

    @PutMapping("/movie/{id}")
    public boolean update(@PathVariable(name="id") String id,
                          @RequestBody MovieDto model)
            throws CustomErrorException {
        return movieService.update(id, model);
    }

    @DeleteMapping("/movie/{id}")
    public void deleteById(@PathVariable(name="id") String id)
            throws CustomErrorException {
        movieService.deleteById(id);
    }
}
