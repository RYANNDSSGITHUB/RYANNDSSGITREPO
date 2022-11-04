package com.dss.login.service;

import com.dss.login.model.Usr;

import java.util.List;

public interface RegisterUsrService {
    public String save(Usr usr);
    public List<Usr> findAllUsers();
}
