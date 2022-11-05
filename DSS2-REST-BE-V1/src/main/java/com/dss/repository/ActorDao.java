package com.dss.repository;

import com.dss.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorDao extends JpaRepository<Actor,String> {
}
