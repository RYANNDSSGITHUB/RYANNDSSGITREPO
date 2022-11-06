package com.dss.login.controller;

import com.dss.login.exception.AbstractException;
import com.dss.login.model.Usr;
import com.dss.login.model.UsrAuth;
import com.dss.login.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dss/auth/")
public class AuthController {

    @Autowired AuthService authService;
    @Autowired Environment environment;

    @GetMapping("/instance")
    public String getInstancePort(){
        return environment.getProperty("local.server.port");
    }

    @PostMapping("/usr/login")
    public boolean login(@RequestBody UsrAuth model) throws AbstractException {
        return authService.login(model);
    }

    @PostMapping("/usr/register")
    public boolean register(@RequestBody Usr user) throws AbstractException {
        return authService.register(user);
    }
}
