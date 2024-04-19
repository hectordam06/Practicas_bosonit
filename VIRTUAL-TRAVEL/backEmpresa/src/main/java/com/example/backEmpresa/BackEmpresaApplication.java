package com.example.backEmpresa;

import com.example.backEmpresa.controller.dto.AutobusInputDto;
import com.example.backEmpresa.domain.Autobus;
import com.example.backEmpresa.repositories.AutobusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class BackEmpresaApplication implements CommandLineRunner {
	@Autowired
	AutobusRepository autobusRepository;
	public static void main(String[] args) {
		SpringApplication.run(BackEmpresaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		AutobusInputDto autobusInputDto = new AutobusInputDto(1,"Barcelona",new Date(2024-1900, Calendar.APRIL,1),14);
		autobusRepository.save(new Autobus(autobusInputDto));
	}
}

