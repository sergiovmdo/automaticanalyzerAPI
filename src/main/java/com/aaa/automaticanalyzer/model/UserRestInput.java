package com.aaa.automaticanalyzer.model;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
public class UserRestInput {
    private String id;
    private String mail;

    @NotNull(message = "Name is needed")
    private String name;

    @NotNull(message = "First Surname is needed")
    private String firstSurname;
    private String secondSurname;
    private String birthDay;
    private String phoneNumber;

    @NotNull(message = "DNI is needed")
    private String DNI;
}
