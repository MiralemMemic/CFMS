package com.project.prisoner.controller;

import com.project.prisoner.model.Prisoner;
import com.project.prisoner.service.PrisonerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/prisoners")
public class PrisonerController {

    @Autowired
    private PrisonerService prisonerService;

    @GetMapping
    public List<Prisoner> getAllPrisoners(){
        return prisonerService.getAllPrisoners();
    }

    @PostMapping
    public ResponseEntity<String> createPrisoner(@RequestBody @Valid Prisoner prisoner){
        return prisonerService.createPrisoner(prisoner);
    }

    @GetMapping("{id}")
    public ResponseEntity<Prisoner> getPrisonerById(@PathVariable long id){
        return prisonerService.getPrisonerById(id);
    }

    @GetMapping("worst")
    public List<Prisoner> getWorstPrisoners(){
        return prisonerService.theWorst();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deletePrisoner(@PathVariable long id){
        return prisonerService.deletePrisoner(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            org.springframework.web.bind.MethodArgumentNotValidException ex) {
        return prisonerService.handleValidationExceptions(ex);
    }
}
