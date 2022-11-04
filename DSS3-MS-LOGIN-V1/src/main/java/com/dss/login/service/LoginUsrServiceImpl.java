package com.dss.login.service;

import com.dss.login.model.Usr;
import com.dss.login.model.UsrCredentials;
import com.dss.login.repository.UsrDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginUsrServiceImpl implements LoginUsrService {

    @Autowired UsrDao usrDao;

    @Override
    public boolean authenticate(UsrCredentials usrCredentials) {
        return false;
    }
}
