package com.dss.login.service;

import com.dss.login.model.Usr;
import com.dss.login.repository.UsrDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterUsrServiceImpl implements RegisterUsrService {

    @Autowired UsrDao usrDao;

    @Override
    public String save(Usr usr) {
        return null;
    }

    @Override
    public List<Usr> findAllUsers() {
        return null;
    }
}
