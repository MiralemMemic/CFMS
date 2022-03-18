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
		Message message = new Message();
		message.setReceiver(1);
		message.setSender(2);
		message.setContent("Hello");
		messageRepository.save(message);

		Message message1 = new Message();
		message1.setReceiver(3);
		message1.setSender(4);
		message1.setContent("Hello V2");
		messageRepository.save(message1);
	}
}
