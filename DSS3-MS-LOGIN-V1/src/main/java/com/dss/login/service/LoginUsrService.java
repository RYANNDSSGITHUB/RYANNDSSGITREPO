package com.dss.login.service;

import com.dss.login.model.UsrCredentials;

public interface LoginUsrService {
    public boolean authenticate(UsrCredentials usrCredentials);
}
