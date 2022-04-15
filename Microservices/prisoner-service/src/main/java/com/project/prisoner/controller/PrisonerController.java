package com.project.prisoner.controller;

import com.project.prisoner.model.Prisoner;
import com.project.prisoner.service.PrisonerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger log = LoggerFactory.getLogger(PrisonerController.class);

    @Autowired
    private PrisonerService prisonerService;

    @GetMapping
    public List<Prisoner> getAllPrisoners(){
        log.info("PrisonerController - getAllPrisoners");
        return prisonerService.getAllPrisoners();
    }

    @PostMapping
    public ResponseEntity<String> createPrisoner(@RequestBody @Valid Prisoner prisoner){
        log.info("PrisonerController - createPrisoner");
        return prisonerService.createPrisoner(prisoner);
    }

    @GetMapping("{id}")
    public ResponseEntity<Prisoner> getPrisonerById(@PathVariable long id){
        log.info("PrisonerController - getPrisonerById");
        return prisonerService.getPrisonerById(id);
    }

    @GetMapping("cell/{id}")
    public List<Prisoner> getPrisonersFromCell(@PathVariable long id){
        log.info("PrisonerController - getPrisonersFromCell");
        return prisonerService.sittingInCell(id);
    }


    @GetMapping("worst")
    public List<Prisoner> getWorstPrisoners(){
        log.info("PrisonerController - getWorstPrisoners");
        return prisonerService.theWorst();
    }

    @GetMapping("most-common-offense")
    public Object mostCommonOffense(){
        log.info("PrisonerController - mostCommonOffense");
        return prisonerService.mostCommonOffense();
    }

    @GetMapping("cells")
    public List cellsNotEmpty(){
        log.info("PrisonerController - cellsNotEmpty");
        return prisonerService.notEmptyCells();
    }

    @GetMapping("coming-out")
    public Object commingOutFirst(){
        log.info("PrisonerController - commingOutFirst");
        return prisonerService.commingOutFirst();
    }

    @GetMapping("where-is-evil")
    public Object whereIsTheWorst(){
        log.info("PrisonerController - whereIsTheWorst");
        return prisonerService.whereIsTheWorst();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deletePrisoner(@PathVariable long id){
        log.info("PrisonerController - deletePrisoner");
        return prisonerService.deletePrisoner(id);
    }



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            org.springframework.web.bind.MethodArgumentNotValidException ex) {
        return prisonerService.handleValidationExceptions(ex);
    }
}
