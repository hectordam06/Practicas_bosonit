package com.bosonit.block11uploaddownloadfiles;

import com.bosonit.block11uploaddownloadfiles.service.FileStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Block11UploadDownloadFilesApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block11UploadDownloadFilesApplication.class, args);
	}
	@Bean
	CommandLineRunner init(FileStorageService fileStorageService){
		return (args) -> {

		};
	}

}
