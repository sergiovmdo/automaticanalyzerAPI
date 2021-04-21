package com.aaa.automaticanalyzer.model;

import lombok.Data;

@Data
public class SimplifiedUser {
    private String mail;
    private String phoneNumber;
    private String language;
    private String name;

    public static SimplifiedUser fromUser(User user){
        SimplifiedUser simplifiedUser = new SimplifiedUser();
        simplifiedUser.setName(user.getName());
        simplifiedUser.setLanguage("Castellano");
        simplifiedUser.setMail(user.getMail());
        return simplifiedUser;
    }
}
