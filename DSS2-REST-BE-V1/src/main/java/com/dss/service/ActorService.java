package com.dss.service;

import com.dss.exception.CustomErrorException;
import com.dss.model.Actor;
import com.dss.repository.BaseDao;

import java.util.List;

public interface ActorService extends BaseService<Actor> {
    public List<Actor> findAll();
    public boolean update(String id, Actor oldModel) throws CustomErrorException;
}
