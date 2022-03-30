package com.project.prisoner;

import com.project.prisoner.model.Prisoner;
import com.project.prisoner.repository.PrisonerRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@OpenAPIDefinition
@EnableEurekaClient
public class PrisonerServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PrisonerServiceApplication.class, args);
	}

	@Autowired
	private PrisonerRepository prisonerRepository;

	@Override
	public void run(String... args) throws Exception {

	}
}
