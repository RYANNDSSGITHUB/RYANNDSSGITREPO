package com.dss.repository;

import com.dss.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewDao extends JpaRepository<Review,String> {
    public Optional<Review> findByMovieId(String id);
}
