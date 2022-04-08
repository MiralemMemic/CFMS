package com.project.prisoner.service;

import com.project.prisoner.exception.ResourceNotFoundException;
import com.project.prisoner.model.Prisoner;
import com.project.prisoner.repository.PrisonerRepository;
import com.project.prisoner.repository.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrisonerService {
    @Autowired
    private PrisonerRepository prisonerRepository;

    @Autowired
    private QueryRepository queryRepository;

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

    public List<Prisoner> theWorst(){
        return queryRepository.findTheMostEvil();
    }

    public List<Prisoner> sittingInCell(long cellId){
        return queryRepository.prisonersSittingInCell(Long.toString(cellId));
    }

    public Object mostCommonOffense(){
        return queryRepository.mostCommonCriminalAct().get(0);
    }

    public List notEmptyCells(){
        return queryRepository.howManyCellsAreNotEmpty();
    }

    public Object commingOutFirst(){
        return queryRepository.comingOutFirst().get(0);
    }

    public Object whereIsTheWorst(){
        return queryRepository.whereIsTheWorstOfThemAll().get(0);
    }

    public void generisiData(){
        Prisoner p = new Prisoner();
        p.setFirstName("Rodrigo");
        p.setLastName("dos Santos");
        p.setCurrentCell(1);
        p.setOffense(2);
        p.setIdentificationNumber(34555);
        p.setLengthOfSentence(4);
        p.setSentenceEvaluation("Još uvijek nervozan");
        prisonerRepository.save(p);

        Prisoner p1 = new Prisoner();
        p1.setFirstName("Miralem");
        p1.setLastName("Memić");
        p1.setCurrentCell(1);
        p1.setOffense(3);
        p1.setLengthOfSentence(10);
        p1.setIdentificationNumber(55555);
        p1.setSentenceEvaluation("Primjeran, krade srca ženskim čuvarima");
        prisonerRepository.save(p1);

        Prisoner p2 = new Prisoner();
        p2.setFirstName("Ivica");
        p2.setLastName("Marica");
        p2.setCurrentCell(5);
        p2.setOffense(6);
        p2.setLengthOfSentence(2);
        p2.setIdentificationNumber(66633);
        p2.setSentenceEvaluation("Ljuti na vuka");
        prisonerRepository.save(p2);


        Prisoner p3 = new Prisoner();
        p3.setFirstName("Vladimir");
        p3.setLastName("Putin");
        p3.setCurrentCell(99);
        p3.setOffense(5);
        p3.setLengthOfSentence(1000);
        p3.setIdentificationNumber(44444);
        p3.setSentenceEvaluation("Ne kaje se");
        prisonerRepository.save(p3);

        Prisoner p4 = new Prisoner();
        p4.setFirstName("Hanka");
        p4.setLastName("Paldum");
        p4.setCurrentCell(2);
        p4.setOffense(4);
        p4.setLengthOfSentence(8);
        p4.setIdentificationNumber(77722);
        p4.setSentenceEvaluation("Pamti još uvijek neke livade i rijeke");
        prisonerRepository.save(p4);

        Prisoner p5 = new Prisoner();
        p5.setFirstName("Kemal");
        p5.setLastName("Halilović");
        p5.setCurrentCell(2);
        p5.setOffense(2);
        p5.setLengthOfSentence(3);
        p5.setIdentificationNumber(71722);
        p5.setSentenceEvaluation("Plače još uvijek jer je ukrao babici štap");
        prisonerRepository.save(p5);


        Prisoner p6 = new Prisoner();
        p6.setFirstName("Michael");
        p6.setLastName("Scofield");
        p6.setCurrentCell(99);
        p6.setOffense(5);
        p6.setLengthOfSentence(8);
        p6.setIdentificationNumber(75522);
        p6.setSentenceEvaluation("Lagan");
        prisonerRepository.save(p6);
    }
}
