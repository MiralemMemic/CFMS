package com.project.prisoner.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prisoners")

public class Prisoner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message =  "First name is required")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message =  "Last name is required")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message =  "Cell number is required")
    @Column(name = "current_cell")
    private int currentCell;

    @NotNull(message =  "Sentence lenght is required")
    @Column(name = "length_of_sentence")
    private int lengthOfSentence;

    @NotNull(message =  "Identification number is required")
    @Column(name = "identification_number")
    private int identificationNumber;

    @NotBlank(message =  "Evaluation is required")
    @Column(name = "sentence_evaluation")
    private String sentenceEvaluation;

    @NotNull(message =  "Offense is required")
    @Column(name = "offense")
    private int offense;
}
