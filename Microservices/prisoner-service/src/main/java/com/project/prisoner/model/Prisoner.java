package com.project.prisoner.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "current_cell")
    private int currentCell;

    @Column(name = "length_of_sentence")
    private int lengthOfSentence;

    @Column(name = "identification_number")
    private int identificationNumber;

    @Column(name = "sentence_evaluation")
    private String sentenceEvaluation;

    @Column(name = "offense")
    private int offense;
}
