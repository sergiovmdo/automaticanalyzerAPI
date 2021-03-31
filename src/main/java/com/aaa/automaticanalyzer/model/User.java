package com.aaa.automaticanalyzer.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "USERS")
@Data
public class User {
    private String mail;
    private String name;
    private String firstSurname;
    private String secondSurname;
    private String birthDate;
    private String phoneNumber;

    @ElementCollection
    private List<Disease> userDiseases;

    @Column(unique = true)
    @Id
    private String dni;

    private String token;
    private String password;

    public void generateAndSetDiseases() {
        Random random = new Random();
        int quantity = random.nextInt(4) + 1;
        List<Disease> diseasesList = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            int disease = random.nextInt(4);
            diseasesList.add(Disease.values()[disease]);
        }

        userDiseases = diseasesList;
    }

}
