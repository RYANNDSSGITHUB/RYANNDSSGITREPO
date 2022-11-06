package com.dss.login.service;

import com.dss.login.exception.AbstractException;
import com.dss.login.model.Usr;
import com.dss.login.model.UsrAuth;

public interface AuthService {
    public boolean login(UsrAuth usrAuth) throws AbstractException;
    public boolean register(Usr usr) throws AbstractException;

    public int countByEmail(String email);
    public int countByContactNo(String contactNo);

}
