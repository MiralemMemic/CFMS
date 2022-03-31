package com.project.prisoner;

import com.project.prisoner.model.Prisoner;
import com.project.prisoner.repository.PrisonerRepository;
import com.project.prisoner.service.PrisonerService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition
@EnableEurekaClient
public class PrisonerServiceApplication implements CommandLineRunner {

	@Bean
	public PrisonerService prisonerService(){
		return new PrisonerService();
	}

	public static void main(String[] args) {
		SpringApplication.run(PrisonerServiceApplication.class, args);
	}

	@Autowired
	private PrisonerRepository prisonerRepository;

	@Override
	public void run(String... args) throws Exception {
		//po potrebi otkomentarisat za podatke u bazu
		//prisonerService().generisiData();
	}
}
