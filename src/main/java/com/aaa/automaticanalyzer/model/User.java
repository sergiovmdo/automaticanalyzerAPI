package com.aaa.automaticanalyzer.model;

import com.aaa.automaticanalyzer.model.analysis.Analysis;
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

    @Column(columnDefinition = "text")
    private String firebaseToken;

    @ElementCollection
    private List<Disease> diseases;

    @Column(unique = true)
    @Id
    private String dni;

    private String token;
    private String password;
    private Language language;

    @ManyToMany
    List<Medication> medications;

    @ManyToMany
    List<Appointment> appointments;

    @ManyToMany
    List<Analysis> analyses;

    public void generateAndSetDiseases() {
        Random random = new Random();
        int quantity = random.nextInt(2) + 1;
        List<Disease> diseasesList = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            int disease = random.nextInt(2);
            if (!diseasesList.contains(Disease.values()[disease]))
                diseasesList.add(Disease.values()[disease]);
        }

        diseases = diseasesList;
    }

    public boolean isMedicated(Disease disease) {
        if (medications.isEmpty())
            return false;

        for (Disease userDisease : diseases) {
            if (userDisease.equals(disease)) {
                for (Medication medication : medications) {
                    if (medication.getDisease().equals(disease))
                        return true;
                }
            }
        }

        return false;
    }

}
