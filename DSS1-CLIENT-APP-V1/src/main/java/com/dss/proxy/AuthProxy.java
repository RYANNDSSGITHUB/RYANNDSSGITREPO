package com.dss.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="dss-login-service")
public interface AuthProxy {

    @GetMapping("/api/dss/auth/instance")
    public String getServiceInstance();
}
