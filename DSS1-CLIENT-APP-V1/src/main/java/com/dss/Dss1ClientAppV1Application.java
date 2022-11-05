package com.dss;

import com.dss.service.ClientService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableFeignClients
public class Dss1ClientAppV1Application {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(Dss1ClientAppV1Application.class, args);
		ClientService clientService = applicationContext.getBean("clientService",ClientService.class);
		System.out.println(clientService.getServiceInstance());
	}
}
