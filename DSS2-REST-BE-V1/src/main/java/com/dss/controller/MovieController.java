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
    public Movie findById(@PathVariable(name="id") String id)
            throws CustomErrorException {
        return movieService.findById(id);
    }

    @PostMapping("/movie")
    public String add(@RequestBody Movie model)
            throws CustomErrorException {
        return movieService.save(model);
    }

    @PutMapping("/movie/{id}")
    public boolean update(@PathVariable(name="id") String id,
                          @RequestBody MovieDto model)
            throws CustomErrorException {
        return movieService.update(id, model);
    }

    @DeleteMapping("/movie/{id}")
    public boolean deleteById(@PathVariable(name="id") String id)
            throws CustomErrorException {
        return movieService.deleteById(id);
    }
}
