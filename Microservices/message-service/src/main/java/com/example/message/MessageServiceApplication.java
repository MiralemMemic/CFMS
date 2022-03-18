package com.example.message;

import com.example.message.model.Message;
import com.example.message.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessageServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MessageServiceApplication.class, args);
	}

	@Autowired
	private MessageRepository messageRepository;

	@Override
	public void run(String... args) throws Exception {
	}
}
