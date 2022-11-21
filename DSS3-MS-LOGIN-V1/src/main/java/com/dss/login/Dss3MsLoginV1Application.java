package com.dss.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class Dss3MsLoginV1Application {

	public static void main(String[] args) {
		SpringApplication.run(Dss3MsLoginV1Application.class, args);
	}

}
