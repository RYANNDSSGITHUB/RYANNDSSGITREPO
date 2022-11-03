package com.dss.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Dss4MsMovieV1Application {

	public static void main(String[] args) {
		SpringApplication.run(Dss4MsMovieV1Application.class, args);
	}

}
