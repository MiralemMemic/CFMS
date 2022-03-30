package com.project.prisoner.service;

import com.project.prisoner.exception.ResourceNotFoundException;
import com.project.prisoner.model.Prisoner;
import com.project.prisoner.repository.PrisonerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrisonerService {
    @Autowired
    private PrisonerRepository prisonerRepository;

    public List<Prisoner> getAllPrisoners(){
        return prisonerRepository.findAll();
    }


    public ResponseEntity<String> createPrisoner(Prisoner prisoner){
        prisonerRepository.save(prisoner);
        return ResponseEntity.ok("Added prisoner into the database");
    }

    public ResponseEntity<Prisoner> getPrisonerById(long id){
        Prisoner prisoner = prisonerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Message not found"));
        return ResponseEntity.ok(prisoner);
    }

    public ResponseEntity<HttpStatus> deletePrisoner(long id){
        Prisoner prisoner = prisonerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Message not found"));
        prisonerRepository.delete(prisoner);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public Map<String, String> handleValidationExceptions(
            org.springframework.web.bind.MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage;
            if(fieldName.equals("offense")) {
                errorMessage = "Offense can't be null or 0";
            }else{
                errorMessage = error.getDefaultMessage();
            }
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
