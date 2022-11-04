package dss.CLIENTAPP.service;

import dss.CLIENTAPP.proxy.FeignProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired FeignProxy feignProxy;

    public String getServiceInstance() {
        return feignProxy.getServiceInstance();
    }
}
