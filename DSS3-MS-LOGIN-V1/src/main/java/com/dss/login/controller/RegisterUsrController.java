package com.dss.login.controller;

import com.dss.login.model.Usr;
import com.dss.login.service.RegisterUsrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dss/register")
public class RegisterUsrController {

    @Autowired RegisterUsrService registerUsrService;
    @Autowired Environment environment;

    @PostMapping("/usr")
    public String add(@RequestBody Usr user){
        return registerUsrService.save(user);
    }

    @GetMapping("/usr")
    public List<Usr> findAllUsers() {
        return registerUsrService.findAllUsers();
    }

    @GetMapping("/instance")
    public String getInstancePort(){
        return environment.getProperty("local.server.port");
    }
}
