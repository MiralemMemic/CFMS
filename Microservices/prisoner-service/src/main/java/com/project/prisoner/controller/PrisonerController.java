package com.project.prisoner.controller;

import com.project.prisoner.model.Prisoner;
import com.project.prisoner.repository.PrisonerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/prisoners")
public class PrisonerController {

    @Autowired
    private PrisonerRepository prisonerRepository;

    @GetMapping
    public List<Prisoner> getAllPrisoners(){
        return prisonerRepository.findAll();
    }
}
