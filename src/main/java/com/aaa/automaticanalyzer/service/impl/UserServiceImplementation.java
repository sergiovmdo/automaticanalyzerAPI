package com.aaa.automaticanalyzer.service.impl;

import com.aaa.automaticanalyzer.mapping.UserMapper;
import com.aaa.automaticanalyzer.model.User;
import com.aaa.automaticanalyzer.model.UserRestInput;
import com.aaa.automaticanalyzer.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {
    @Override
    public User createUser(UserRestInput input) {
        User user = UserMapper.createUserFromRestInput(input);
        user.generateAndSetDiseases();
        return user;
    }
}
