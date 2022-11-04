package com.dss.review.repository;

import com.dss.review.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieDao extends JpaRepository<Movie,String> {
}
