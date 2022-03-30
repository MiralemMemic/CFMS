package com.project.notification;

import com.project.notification.model.Notification;
import com.project.notification.repository.NotificationRepository;
import com.project.notification.service.NotificationService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition
public class NotificationServiceApplication implements CommandLineRunner {

	@Bean
	public NotificationService notificationService(){
		return new NotificationService();
	}

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

	@Autowired
	private NotificationRepository notificationRepository;

	@Override
	public void run(String... args) throws Exception {
		Notification notification = new Notification();
		notification.setJail(1);
		notification.setText("Hello");
		notificationRepository.save(notification);
	}
}
