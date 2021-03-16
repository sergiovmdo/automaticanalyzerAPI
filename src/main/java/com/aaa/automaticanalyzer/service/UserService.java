package com.aaa.automaticanalyzer.service;


import com.aaa.automaticanalyzer.exceptions.InvalidBirthDate;
import com.aaa.automaticanalyzer.exceptions.InvalidDNI;
import com.aaa.automaticanalyzer.exceptions.InvalidMail;
import com.aaa.automaticanalyzer.model.User;
import com.aaa.automaticanalyzer.model.UserRestInput;

public interface UserService {
    /**
     * Creates a new user from the given information.
     * @param input Data to create the new user.
     * @return Created user.
     * @throws InvalidDNI
     * @throws InvalidMail
     * @throws InvalidBirthDate
     */
    User createUser(final UserRestInput input);
}
