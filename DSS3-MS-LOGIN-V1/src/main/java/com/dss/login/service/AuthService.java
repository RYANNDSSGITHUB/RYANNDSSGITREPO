package com.dss.login.service;

import com.dss.login.domain.exception.AbstractException;
import com.dss.login.domain.model.Usr;
import com.dss.login.domain.dto.AuthRequest;

public interface AuthService {
    public String login(AuthRequest authRequest) throws AbstractException;
    public boolean register(Usr usr) throws AbstractException;

    public int countByEmail(String email);
    public int countByContactNo(String contactNo);

}
