package com.bosonit.crudrelaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CrudRelacionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudRelacionesApplication.class, args);
	}

}
