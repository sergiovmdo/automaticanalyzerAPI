package com.aaa.automaticanalyzer.api.user.business;


import com.aaa.automaticanalyzer.api.user.domain.PasswordRestInput;
import com.aaa.automaticanalyzer.exceptions.InvalidBirthDate;
import com.aaa.automaticanalyzer.exceptions.InvalidDNI;
import com.aaa.automaticanalyzer.exceptions.InvalidMail;
import com.aaa.automaticanalyzer.model.User;
import com.aaa.automaticanalyzer.api.user.domain.UserRestInput;
import org.springframework.http.ResponseEntity;

import javax.swing.text.html.Option;
import java.util.Optional;

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

    Optional<User> validateToken(String token);

    Optional<User> getUserByDNI(final String dni);

    String hashPassword(String password);

    ResponseEntity<Void> changePassword(PasswordRestInput password, String dni);
}
