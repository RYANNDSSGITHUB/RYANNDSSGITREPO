package com.dss.repository;

import com.dss.model.AbstractBaseModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseDao<T extends AbstractBaseModel> extends JpaRepository<T, String> {
}
