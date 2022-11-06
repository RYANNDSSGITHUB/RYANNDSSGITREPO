package com.dss.service;

import com.dss.model.AbstractBaseModel;

import java.util.List;
import java.util.Optional;

public interface BaseService<T extends AbstractBaseModel> {
    T save(T model);
    List<T> findAll();
    Optional<T> findById(String id);
    void deleteById(String id);
}
