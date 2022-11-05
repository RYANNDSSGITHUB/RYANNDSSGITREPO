package com.dss.service;

import com.dss.exception.CustomErrorException;
import com.dss.model.Actor;

import java.util.List;

public interface ActorService {
    public Actor findById(String id);
    public List<Actor> findAll();
    public String save(Actor actor);
    public boolean update(String id, Actor oldModel) throws CustomErrorException;
    public boolean deleteById(String id) throws CustomErrorException;
}
