package com.dss.movie.repository;

import com.dss.movie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieDao extends JpaRepository<Movie,String> {
}
