package com.project.quiz.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String category;
    private String option_1;
    private String option_2;
    private String option_3;
    private String option_4;
    private String questionTitle;
    private String correctAns;
    private String difficultyLvl;
}
