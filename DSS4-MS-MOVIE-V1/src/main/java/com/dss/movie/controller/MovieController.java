package com.dss.movie.controller;

import com.dss.movie.model.Movie;
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
                          @RequestBody Movie model){
        return movieService.update(model);
    }

    @DeleteMapping("/movie")
    public boolean deleteById(@RequestBody String id){
        return movieService.deleteById(id);
    }
}
