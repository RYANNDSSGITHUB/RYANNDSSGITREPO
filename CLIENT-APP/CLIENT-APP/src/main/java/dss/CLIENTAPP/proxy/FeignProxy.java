package dss.CLIENTAPP.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="dss-login-service")
public interface FeignProxy {

    @GetMapping("/api/dss/register/instance")
    public String getServiceInstance();
}
