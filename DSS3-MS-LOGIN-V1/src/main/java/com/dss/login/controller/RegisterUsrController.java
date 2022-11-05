package com.dss.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dss/register")
public class RegisterUsrController {

    @Autowired Environment environment;


    @GetMapping("/instance")
    public String getInstancePort(){
        return environment.getProperty("local.server.port");}
}
