package com.aaa.automaticanalyzer.api.user.business;

import com.aaa.automaticanalyzer.api.user.rest.mapping.UserMapper;
import com.aaa.automaticanalyzer.model.User;
import com.aaa.automaticanalyzer.api.user.domain.UserRestInput;
import com.aaa.automaticanalyzer.repository.UserRepository;
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
