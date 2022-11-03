package com.dss.actor.controller;

import com.dss.actor.model.Actor;
import com.dss.actor.service.ActorService;
import com.dss.movie.exception.AbstractRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/dss")
public class ActorController {

    @Autowired ActorService actorService;

    @GetMapping("/actor")
    public List<Actor> findAll(){
        return actorService.findAll();
    }

    @GetMapping("/actor/{id}")
    public Actor findById(@PathVariable(name="id") String id){
        return actorService.findById(id);
    }

    @PostMapping("/actor")
    public String add(@RequestBody Actor model){
        return actorService.save(model);
    }

    @PutMapping("/actor/{id}")
    public boolean update(@PathVariable(name="id") String id,
                         @RequestBody Actor model)
            throws AbstractRuntimeException{
        return actorService.update(id, model);
    }

    @DeleteMapping("/actor/{id}")
    public boolean deleteById(@PathVariable(name="id") String id)
            throws AbstractRuntimeException {
        return actorService.deleteById(id);
    }
}
