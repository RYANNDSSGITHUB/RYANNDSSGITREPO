package com.dss.login.repository;

import com.dss.login.domain.model.Usr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthDao extends JpaRepository<Usr, String> {
    public Optional<Usr> findByEmail(String email);
    public int countByEmail(String email);
    public int countByContactNo(String contactNo);
    public int countByEmailAndPassword(String email, String password);
}
