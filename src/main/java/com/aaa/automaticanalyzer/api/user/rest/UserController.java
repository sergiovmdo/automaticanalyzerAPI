package com.aaa.automaticanalyzer.api.user.rest;

import com.aaa.automaticanalyzer.api.user.business.UserService;
import com.aaa.automaticanalyzer.api.user.domain.LanguageRestInput;
import com.aaa.automaticanalyzer.api.user.domain.PasswordRestInput;
import com.aaa.automaticanalyzer.common.AuthAwareRestController;
import com.aaa.automaticanalyzer.exceptions.InvalidPassword;
import com.aaa.automaticanalyzer.exceptions.UserNotFound;
import com.aaa.automaticanalyzer.model.FCMToken;
import com.aaa.automaticanalyzer.model.SimplifiedUser;
import com.aaa.automaticanalyzer.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
@AuthAwareRestController
public class UserController {

    private final UserService userService;

    @GetMapping
    public SimplifiedUser getUser(final User user) {
        return userService.getSimplifiedUserByDNI(user);
    }

    @PutMapping
    public void changePassword(@RequestBody final PasswordRestInput password, final User user) {
        try {
            userService.changePassword(password, user);
        } catch (InvalidPassword e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/fcmtoken")
    public ResponseEntity<Void> insertFCMToken(@RequestBody final FCMToken token, final User user) {
        return userService.setFCMToken(token, user);
    }

    @PutMapping("/language")
    public ResponseEntity<Void> changeLanguage(@RequestBody final LanguageRestInput languageRestInput, final User user) {
        return userService.changeLanguage(languageRestInput, user.getDni());
    }
}
