package com.dss.repository;

import com.dss.model.Usr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthDao extends BaseDao<Usr> {
    public int countByEmail(String email);
    public int countByContactNo(String contactNo);
    public int countByEmailAndPassword(String email, String password);
}
