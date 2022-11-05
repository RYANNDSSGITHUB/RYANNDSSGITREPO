package com.dss.login.repository;

import com.dss.login.model.Usr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthDao extends JpaRepository<Usr, String> {
    public int countByEmail(String email);
    public int countByContactNo(String contactNo);
    public int countByEmailAndPassword(String email, String password);
}
