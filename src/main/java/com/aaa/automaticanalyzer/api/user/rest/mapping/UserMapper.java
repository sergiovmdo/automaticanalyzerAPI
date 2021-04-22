package com.aaa.automaticanalyzer.api.user.rest.mapping;

import com.aaa.automaticanalyzer.model.Language;
import com.aaa.automaticanalyzer.model.SimplifiedUser;
import com.aaa.automaticanalyzer.model.User;
import com.aaa.automaticanalyzer.api.user.domain.UserRestInput;

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

        return user;
    }
    
}
