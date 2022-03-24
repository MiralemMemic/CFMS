package com.project.notification;

import com.project.notification.model.Notification;
import com.project.notification.repository.NotificationRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class NotificationServiceApplication implements CommandLineRunner {

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
