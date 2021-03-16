package com.aaa.automaticanalyzer.model;

import lombok.Data;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
//@Table(name = "USERS")
@Data
public class User {
    private String mail;
    private String name;
    private String firstSurname;
    private String secondSurname;
    private String birthDay;
    private String phoneNumber;

    @ElementCollection
    private List<Diseases> userDiseases;

    @Column(unique = true)
    @Id
    private String DNI;

    public void generateAndSetDiseases() {
        Random random = new Random();
        int quantity = random.nextInt(4) + 1;
        List<Diseases> diseasesList = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            int disease = random.nextInt(4) + 1;
            diseasesList.add(Diseases.values()[disease]);
        }

        userDiseases = diseasesList;
    }

}
