package com.dss.controller;

import com.dss.exception.CustomErrorException;
import com.dss.model.Usr;
import com.dss.model.UsrAuth;
import com.dss.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dss/auth/")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/usr/login")
    public boolean login(@RequestBody UsrAuth model) throws CustomErrorException {
        return authService.login(model);
    }

    @PostMapping("/usr/register")
    public boolean register(@RequestBody Usr user) throws CustomErrorException {
        return authService.register(user);
    }

    @GetMapping("/usr")
    public List<Usr> findAllUsers() {
        return authService.findAll();
    }
}
