package com.aaa.automaticanalyzer.api.user.domain;

import lombok.Data;

@Data
public class LoginRestInput {
    private String dni;
    private String password;
}
