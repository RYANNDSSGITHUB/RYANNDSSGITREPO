package com.dss.actor.service;

import com.dss.actor.model.Actor;

import java.util.List;

public interface ActorService {
    public Actor findById(String id);
    public List<Actor> findAll();
    public String save(Actor actor);
    public boolean update(Actor actor);
    public boolean deleteById(String id);
}
