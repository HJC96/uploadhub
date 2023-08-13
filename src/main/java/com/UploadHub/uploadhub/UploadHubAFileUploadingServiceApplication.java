package com.UploadHub.uploadhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UploadHubAFileUploadingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UploadHubAFileUploadingServiceApplication.class, args);
	}

}
