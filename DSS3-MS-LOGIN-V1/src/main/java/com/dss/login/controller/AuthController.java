package com.dss.login.controller;

import com.dss.login.domain.dto.AuthRequest;
import com.dss.login.domain.exception.AbstractException;
import com.dss.login.domain.model.Usr;
import com.dss.login.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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
    public ResponseEntity<String> login(@RequestBody AuthRequest request) throws AbstractException {
        return new ResponseEntity<String>(authService.login(request), HttpStatus.OK);
    }

    @PostMapping("/usr/register")
    public boolean register(@RequestBody Usr user) throws AbstractException {
        return authService.register(user);
    }
}
