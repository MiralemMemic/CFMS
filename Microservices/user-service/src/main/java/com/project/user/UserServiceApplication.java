package com.project.user;

import com.project.user.model.User;
import com.project.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setFirstName("Miralem");
		user.setLastName("Memic");
		user.setUsername("mmemic");
		user.setPassword("miralem2022");
		user.setEmail("mememic@hotmail.com");
		user.setRole("warden");
		userRepository.save(user);

		User user1 = new User();
		user1.setFirstName("Kenan");
		user1.setLastName("Omic");
		user1.setUsername("komic");
		user1.setPassword("kenan2022");
		user1.setEmail("komic@hotmail.com");
		user1.setRole("warden");
		userRepository.save(user1);
	}
}