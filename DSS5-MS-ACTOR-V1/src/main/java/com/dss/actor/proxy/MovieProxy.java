package com.dss.actor.proxy;

import com.dss.actor.model.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="movie-service", url="localhost:9007")
public interface MovieProxy {

    @GetMapping("/api/dss/movie/")
    public List<Movie> findAll();
}
