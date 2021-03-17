package com.aaa.automaticanalyzer.model;

import lombok.Data;
import lombok.Setter;

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
    private List<Diseases> userDiseases;

    @Column(unique = true)
    @Id
    private String dni;

    public void generateAndSetDiseases() {
        Random random = new Random();
        int quantity = random.nextInt(4) + 1;
        List<Diseases> diseasesList = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            int disease = random.nextInt(4);
            diseasesList.add(Diseases.values()[disease]);
        }

        userDiseases = diseasesList;
    }

}
