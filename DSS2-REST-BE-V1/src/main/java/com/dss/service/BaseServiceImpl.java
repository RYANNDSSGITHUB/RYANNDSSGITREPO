package com.dss.service;

import com.dss.model.AbstractBaseModel;
import com.dss.repository.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BaseServiceImpl<T extends AbstractBaseModel> implements BaseService<T> {

    @Autowired
    BaseDao<T> baseDao;

    @Override
    public T save(T model) {
        return (T) baseDao.save(model);
    }

    @Override
    public List<T> findAll() {
        return baseDao.findAll();
    }

    @Override
    public Optional<T> findById(String id) {
        return baseDao.findById(id);
    }

    @Override
    public void deleteById(String id) {
        baseDao.deleteById(id);
    }
}
