package com.project.prisoner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.prisoner.controller.PrisonerController;
import com.project.prisoner.exception.ResourceNotFoundException;
import com.project.prisoner.model.Prisoner;
import com.project.prisoner.repository.PrisonerRepository;
import com.project.prisoner.service.PrisonerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PrisonerController.class)
public class PrisonerControllerTest {

    @MockBean
    private PrisonerService prisonerService;

    @MockBean
    private PrisonerRepository prisonerRepository;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("List all Prisoners when making GET request")
    public void ListAllPrisoners() throws Exception{

        Prisoner prisoner1 = new Prisoner();
        prisoner1.setId(1);
        prisoner1.setFirstName("Kem");
        prisoner1.setLastName("Hal");
        prisoner1.setCurrentCell(1);
        prisoner1.setLengthOfSentence(5);
        prisoner1.setIdentificationNumber(1);
        prisoner1.setSentenceEvaluation("Ok");
        prisoner1.setOffense(1);

        Prisoner prisoner2 = new Prisoner();
        prisoner2.setId(2);
        prisoner2.setFirstName("Far");
        prisoner2.setLastName("Meh>");
        prisoner2.setCurrentCell(1);
        prisoner2.setLengthOfSentence(5);
        prisoner2.setIdentificationNumber(1);
        prisoner2.setSentenceEvaluation("Ok");
        prisoner2.setOffense(1);

        Mockito.when(prisonerService.getAllPrisoners()).thenReturn(Arrays.asList(prisoner1,prisoner2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/prisoners"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    @DisplayName("Get prisoner by id with GET")
    public void GetAPrisoner() throws Exception{

        Prisoner prisoner = new Prisoner();
        prisoner.setId(1);
        prisoner.setFirstName("Kem");
        prisoner.setLastName("Hal");
        prisoner.setCurrentCell(1);
        prisoner.setLengthOfSentence(5);
        prisoner.setIdentificationNumber(1);
        prisoner.setSentenceEvaluation("Ok");
        prisoner.setOffense(1);


        Mockito.when(prisonerService.getPrisonerById(1l)).thenReturn(ResponseEntity.ok(prisoner));


        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/prisoners/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("Kem")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is("Hal")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currentCell", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lengthOfSentence", Matchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.identificationNumber", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sentenceEvaluation", Matchers.is("Ok")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.offense", Matchers.is(1)));

    }

    @Test
    @DisplayName("Delete a prisoner when making DELETE")
    public void DeletePrisoner() throws Exception{

        Prisoner prisoner = new Prisoner();
        prisoner.setId(1);
        prisoner.setFirstName("Kem");
        prisoner.setLastName("Hal");
        prisoner.setCurrentCell(1);
        prisoner.setLengthOfSentence(5);
        prisoner.setIdentificationNumber(1);
        prisoner.setSentenceEvaluation("Ok");
        prisoner.setOffense(1);

        Mockito.when(prisonerRepository.findById(1l)).thenReturn(Optional.of(prisoner));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/prisoners/1"))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }

    @Test
    public void testErrorGet() throws Exception {

        Prisoner prisoner = new Prisoner();
        prisoner.setId(1);

        Mockito.when(prisonerService.getPrisonerById(1l)).thenThrow(new ResourceNotFoundException("Prisoner not found"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/prisoners/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    @DisplayName("Send error when prisoner information is invalid")
    public void testInvalidPost() throws Exception {


        Prisoner prisoner = new Prisoner();
        prisoner.setId(1);


        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(prisoner);


        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .post("/api/v1/prisoners")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        this.mockMvc.perform(builder)
                .andExpect(status().is4xxClientError());
    }
    @Test
    @DisplayName("Should Post  - /api/prisoners/")
    public void testPost() throws Exception {

        Prisoner prisoner = new Prisoner();
        prisoner.setId(1);
        prisoner.setFirstName("Kem");
        prisoner.setLastName("Hal");
        prisoner.setCurrentCell(1);
        prisoner.setLengthOfSentence(5);
        prisoner.setIdentificationNumber(1);
        prisoner.setSentenceEvaluation("Ok");
        prisoner.setOffense(1);

        Mockito.when(prisonerService.createPrisoner(prisoner)).thenReturn(ResponseEntity.ok("Prisoner added"));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(prisoner);


        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/prisoners")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    @DisplayName("Get worst prisoners")
    public void GetWorstPrisoners() throws Exception{

        Prisoner prisoner1 = new Prisoner();
        prisoner1.setId(1);
        prisoner1.setFirstName("Kem");
        prisoner1.setLastName("Hal");
        prisoner1.setCurrentCell(1);
        prisoner1.setLengthOfSentence(5);
        prisoner1.setIdentificationNumber(1);
        prisoner1.setSentenceEvaluation("Ok");
        prisoner1.setOffense(1);


        Mockito.when(prisonerService.theWorst()).thenReturn(Arrays.asList(prisoner1));


        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/prisoners/worst"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"firstName\":\"Kem\",\"lastName\":\"Hal\",\"currentCell\":1,\"lengthOfSentence\":5,\"identificationNumber\":1,\"sentenceEvaluation\":\"Ok\",\"offense\":1}]"));
    }

    @Test
    @DisplayName("Get most common offense prisoners")
    public void GetMostCommonOffensePrisoners() throws Exception{

        Mockito.when(prisonerService.mostCommonOffense()).thenReturn(1);


        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/prisoners/most-common-offense"))
                .andExpect(status().isOk())
                .andExpect(content().json("1"));
    }

    @Test
    @DisplayName("Get cells not empty")
    public void GetCells() throws Exception{

        Mockito.when(prisonerService.notEmptyCells()).thenReturn(Arrays.asList(1,4,5));


        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/prisoners/cells"))
                .andExpect(status().isOk())
                .andExpect(content().json("[1,4,5]"));
    }

    @Test
    @DisplayName("Get coming out first")
    public void GetComingOut() throws Exception{


        Prisoner prisoner1 = new Prisoner();
        prisoner1.setId(1);
        prisoner1.setFirstName("Kem");
        prisoner1.setLastName("Hal");
        prisoner1.setCurrentCell(1);
        prisoner1.setLengthOfSentence(5);
        prisoner1.setIdentificationNumber(1);
        prisoner1.setSentenceEvaluation("Ok");
        prisoner1.setOffense(1);

        Mockito.when(prisonerService.commingOutFirst()).thenReturn((prisoner1));


        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/prisoners/coming-out"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"firstName\":\"Kem\",\"lastName\":\"Hal\",\"currentCell\":1,\"lengthOfSentence\":5,\"identificationNumber\":1,\"sentenceEvaluation\":\"Ok\",\"offense\":1}"));
    }

    @Test
    @DisplayName("Get worst")
    public void GetWorst() throws Exception{

        Prisoner prisoner1 = new Prisoner();
        prisoner1.setId(1);
        prisoner1.setFirstName("Kem");
        prisoner1.setLastName("Hal");
        prisoner1.setCurrentCell(1);
        prisoner1.setLengthOfSentence(5);
        prisoner1.setIdentificationNumber(1);
        prisoner1.setSentenceEvaluation("Ok");
        prisoner1.setOffense(1);

        Mockito.when(prisonerService.whereIsTheWorst()).thenReturn((prisoner1));


        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/prisoners/where-is-evil"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"firstName\":\"Kem\",\"lastName\":\"Hal\",\"currentCell\":1,\"lengthOfSentence\":5,\"identificationNumber\":1,\"sentenceEvaluation\":\"Ok\",\"offense\":1}"));
    }


}
