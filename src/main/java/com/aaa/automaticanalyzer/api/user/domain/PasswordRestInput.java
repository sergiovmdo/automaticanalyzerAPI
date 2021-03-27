package com.aaa.automaticanalyzer.api.user.domain;

import lombok.Data;

@Data
public class PasswordRestInput {
    String currentPassword;
    String password;
}
