package com.dss.actor.service;

import com.dss.movie.model.Actor;

import java.util.List;

public interface ActorService {
    public void deleteById(String id);
    public Actor save(Actor movie);
    public List<Actor> findAll();
}
