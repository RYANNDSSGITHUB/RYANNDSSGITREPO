package com.dss.actor.repository;

import com.dss.movie.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorDao extends JpaRepository<Actor,String> {
}
