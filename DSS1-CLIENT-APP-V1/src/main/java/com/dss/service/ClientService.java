package com.dss.service;

import com.dss.proxy.AuthProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired AuthProxy authProxy;

    public String getServiceInstance() {
        return authProxy.getServiceInstance();
    }
}
