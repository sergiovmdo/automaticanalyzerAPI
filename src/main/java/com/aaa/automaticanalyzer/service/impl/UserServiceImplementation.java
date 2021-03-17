package com.aaa.automaticanalyzer.service.impl;

import com.aaa.automaticanalyzer.mapping.UserMapper;
import com.aaa.automaticanalyzer.model.User;
import com.aaa.automaticanalyzer.model.UserRestInput;
import com.aaa.automaticanalyzer.repository.UserRepository;
import com.aaa.automaticanalyzer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(UserRestInput input) {
        User user = UserMapper.createUserFromRestInput(input);
        user.generateAndSetDiseases();

        userRepository.save(user);

        return user;
    }
}
