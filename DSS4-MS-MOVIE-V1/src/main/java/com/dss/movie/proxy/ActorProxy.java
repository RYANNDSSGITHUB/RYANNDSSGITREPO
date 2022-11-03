package com.dss.movie.proxy;

import com.dss.movie.model.Actor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="actor-service", url="localhost:9006")
public interface ActorProxy {

    @GetMapping("/api/dss/actor/")
    public List<Actor> findAll();
}
