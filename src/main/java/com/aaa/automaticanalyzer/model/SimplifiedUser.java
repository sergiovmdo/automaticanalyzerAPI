package com.aaa.automaticanalyzer.model;

import lombok.Data;

@Data
public class SimplifiedUser {
    private String dni;
    private String name;

    public static SimplifiedUser fromUser(User user){
        SimplifiedUser simplifiedUser = new SimplifiedUser();
        simplifiedUser.setDni(user.getDni());
        simplifiedUser.setName(user.getName());
        return simplifiedUser;
    }
}
