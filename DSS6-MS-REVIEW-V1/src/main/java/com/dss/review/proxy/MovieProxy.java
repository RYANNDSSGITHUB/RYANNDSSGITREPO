package com.dss.review.proxy;

import com.dss.review.model.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name="movie-service", url="localhost:9007")
public interface MovieProxy {

    @GetMapping("/api/dss/movie/{id}")
    public Optional<Movie> findById(@PathVariable(name="id") String id);
}
