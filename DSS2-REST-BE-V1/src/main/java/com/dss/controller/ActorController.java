package com.dss.controller;

import com.dss.exception.CustomErrorException;
import com.dss.model.Actor;
import com.dss.service.ActorService;
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
        return actorService.findById(id).get();
    }

    @PostMapping("/actor")
    public Actor add(@RequestBody Actor model){
        return actorService.save(model);
    }

    @PutMapping("/actor/{id}")
    public boolean update(@PathVariable(name="id") String id,
                         @RequestBody Actor model)
            throws CustomErrorException {
        return actorService.update(id, model);
    }

    @DeleteMapping("/actor/{id}")
    public void deleteById(@PathVariable(name="id") String id) {
        actorService.deleteById(id);
    }
}
