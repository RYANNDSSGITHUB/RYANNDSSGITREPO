package com.dss.login.repository;

import com.dss.login.model.Usr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsrDao extends JpaRepository<Usr, String> {
}
