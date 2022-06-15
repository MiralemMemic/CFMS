package com.example.message;

//import com.example.message.config.MessageStatusUpdateHandler;
import com.example.message.repository.MessageRepository;
import com.example.message.service.MessageService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@Configuration
//@EnableAutoConfiguration
//@ComponentScan
@OpenAPIDefinition
public class MessageServiceApplication implements CommandLineRunner {

	@Bean
	public MessageService messageServices(){
		return new MessageService();
	}

	public static void main(String[] args) {
		SpringApplication.run(MessageServiceApplication.class, args);
	}

	@Autowired
	private MessageRepository messageRepository;

	@Override
	public void run(String... args) throws Exception {
	}
}
