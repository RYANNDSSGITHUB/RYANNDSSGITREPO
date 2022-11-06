package com.dss.service;

import com.dss.exception.CustomErrorException;
import com.dss.model.Usr;
import com.dss.model.UsrAuth;

import java.util.List;

public interface AuthService extends BaseService<Usr> {
    public boolean login(UsrAuth usrAuth) throws CustomErrorException;
    public boolean register(Usr usr) throws CustomErrorException;

    public int countByEmail(String email);
    public int countByContactNo(String contactNo);

}
