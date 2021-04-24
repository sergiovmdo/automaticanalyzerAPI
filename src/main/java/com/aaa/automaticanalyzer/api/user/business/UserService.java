package com.aaa.automaticanalyzer.api.user.business;


import com.aaa.automaticanalyzer.api.user.domain.LanguageRestInput;
import com.aaa.automaticanalyzer.api.user.domain.PasswordRestInput;
import com.aaa.automaticanalyzer.exceptions.*;
import com.aaa.automaticanalyzer.model.*;
import com.aaa.automaticanalyzer.api.user.domain.UserRestInput;
import org.springframework.http.ResponseEntity;

import javax.swing.text.html.Option;
import java.util.List;
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

    SimplifiedUser getSimplifiedUserByDNI(final User user);

    String hashPassword(String password);

    void changePassword(PasswordRestInput password, final User user) throws InvalidPassword;

    ResponseEntity<Void> changeLanguage(LanguageRestInput language, String dni);

    ResponseEntity<Void> setFCMToken(FCMToken token, final User user);

    List<Medication> getUserMedication(User user);

    Medication getHypothyroidismMedication();

    Medication getHypercholesterolemiaMedication();
}
