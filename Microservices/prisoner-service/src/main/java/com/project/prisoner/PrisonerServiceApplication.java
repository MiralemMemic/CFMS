package com.project.prisoner;

import com.project.prisoner.model.Prisoner;
import com.project.prisoner.repository.PrisonerRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class PrisonerServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PrisonerServiceApplication.class, args);
	}

	@Autowired
	private PrisonerRepository prisonerRepository;

	@Override
	public void run(String... args) throws Exception {
		Prisoner prisoner = new Prisoner();
		prisoner.setFirstName("Kenan");
		prisoner.setLastName("Omic");
		prisoner.setCurrentCell(1);
		prisoner.setLengthOfSentence(10);
		prisoner.setIdentificationNumber(123);
		prisoner.setSentenceEvaluation("some evaluation");
		prisoner.setOffense(2);
		prisonerRepository.save(prisoner);

		Prisoner prisoner1 = new Prisoner();
		prisoner1.setFirstName("Kemal");
		prisoner1.setLastName("Halilovic");
		prisoner1.setCurrentCell(2);
		prisoner1.setLengthOfSentence(15);
		prisoner1.setIdentificationNumber(225);
		prisoner1.setSentenceEvaluation("some different evaluation");
		prisoner1.setOffense(2);
		prisonerRepository.save(prisoner1);
	}
}
