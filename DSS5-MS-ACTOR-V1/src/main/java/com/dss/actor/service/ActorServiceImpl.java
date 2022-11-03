package com.dss.actor.service;

import com.dss.actor.repository.ActorDao;
import com.dss.movie.model.Actor;
import com.dss.movie.model.Movie;
import com.dss.movie.repository.MovieDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ActorServiceImpl implements ActorService {

    @Autowired ActorDao actorDao;

    @Override
    public void deleteById(String id) {
        actorDao.deleteById(id);
    }

    @Override
    public Actor save(Actor actor) {
        return actorDao.save(actor);
    }

    @Override
    public List<Actor> findAll() {
        return actorDao.findAll();
    }
}
