package com.aaa.automaticanalyzer.api.user.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserRestInput {
    private String mail;

    @NotNull(message = "Name is needed")
    private String name;

    @NotNull(message = "First Surname is needed")
    private String firstSurname;
    private String secondSurname;
    private String birthDate;
    private String phoneNumber;
    private String password;

    @NotNull(message = "DNI is needed")
    private String dni;
}
