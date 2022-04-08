package com.project.user;

import com.project.user.model.User;
import com.project.user.repository.UserRepository;
import com.project.user.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@OpenAPIDefinition
public class UserServiceApplication implements CommandLineRunner {

	@Bean
	public UserService getUserService(){
		return new UserService();
	}

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}


	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

//	@Autowired
//	private UserRepository userRepository;


	@Override
	public void run(String... args) throws Exception {

		//getUserService().generateData();

	}

/*
	@Bean
	public CommandLineRunner run(UserRepository userRepository) throws Exception {
		return (String[] args) -> {
			User user = new User();
			user.setFirstName("Miralem");
			user.setLastName("Memic");
			user.setUsername("mmemic");
			user.setPassword("miralem2022");
			user.setEmail("mememic@hotmail.com");
			user.setRole("warden");

			User user1 = new User();
			user1.setFirstName("Kenan");
			user1.setLastName("Omic");
			user1.setUsername("komic");
			user1.setPassword("kenan2022");
			user1.setEmail("komic@hotmail.com");
			user1.setRole("warden");

			userRepository.save(user);
			userRepository.save(user1);
			userRepository.findAll().forEach(System.out::println);
		};
	}

*/

}