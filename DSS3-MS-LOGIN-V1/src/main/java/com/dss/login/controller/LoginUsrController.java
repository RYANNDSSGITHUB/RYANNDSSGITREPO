package com.dss.login.controller;

import com.dss.login.model.Usr;
import com.dss.login.model.UsrCredentials;
import com.dss.login.service.LoginUsrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dss/login")
public class LoginUsrController {

    @Autowired LoginUsrService loginUsrService;

    @GetMapping("/usr")
    public boolean login(@RequestBody UsrCredentials model){
        return loginUsrService.authenticate(model);
    }

}
