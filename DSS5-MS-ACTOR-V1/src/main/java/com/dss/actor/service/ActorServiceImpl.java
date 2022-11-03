package com.dss.actor.service;

import com.dss.actor.repository.ActorDao;
import com.dss.actor.model.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired ActorDao actorDao;

    @Override
    public Actor findById(String id) {
        Optional<Actor> actor = actorDao.findById(id);
        if(actor.isPresent()){
            return actor.get();
        }
        return null;
    }

    @Override
    public List<Actor> findAll() {
        return actorDao.findAll();
    }

    @Override
    public String save(Actor actor) {
        actor = actorDao.save(actor);
        return actor.getId();
    }

    @Override
    public boolean update(Actor actor) {
        Boolean isSuccess = true;
        try {
            if(actor.getId()==null){
                throw new Exception();
            } else {
                actorDao.save(actor);
            }
        } catch(Exception e){
            isSuccess = false;
            e.printStackTrace();
        }
        return isSuccess;
    }

    @Override
    public boolean deleteById(String id) {
        Boolean isSuccess = true;
        try {
            actorDao.deleteById(id);
        } catch (Exception e){
            isSuccess = false;
        }
        return isSuccess;
    }
}
