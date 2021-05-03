package com.aaa.automaticanalyzer.api.user.rest.mapping;

import com.aaa.automaticanalyzer.api.user.domain.UserRestInput;
import com.aaa.automaticanalyzer.model.Language;
import com.aaa.automaticanalyzer.model.User;

import java.util.ArrayList;

public class UserMapper {
    public static User createUserFromRestInput(UserRestInput input) {
        User user = new User();
        user.setBirthDate(input.getBirthDate());
        user.setDni(input.getDni());
        user.setFirstSurname(input.getFirstSurname());
        user.setSecondSurname(input.getSecondSurname());
        user.setMail(input.getMail());
        user.setName(input.getName());
        user.setPhoneNumber(input.getPhoneNumber());
        user.setLanguage(Language.CASTELLANO);
        user.setAppointments(new ArrayList<>());
        user.setAnalyses(new ArrayList<>());

        return user;
    }
    
}
