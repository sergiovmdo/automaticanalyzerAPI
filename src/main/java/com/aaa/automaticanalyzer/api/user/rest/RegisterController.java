package com.aaa.automaticanalyzer.api.user.rest;

import com.aaa.automaticanalyzer.api.user.domain.UserRestInput;
import com.aaa.automaticanalyzer.api.user.business.UserService;
import com.aaa.automaticanalyzer.exceptions.UserAlreadyExists;
import com.aaa.automaticanalyzer.model.TokenResponse;
import com.aaa.automaticanalyzer.model.User;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/register")
@AllArgsConstructor
public class RegisterController {

    private final UserService userService;

    @PostMapping
    public TokenResponse createUser(@RequestBody final UserRestInput userRestInput) {
        User user = null;
        try {
            user = userService.createUser(userRestInput);
            return new TokenResponse(user.getToken());
        } catch (UserAlreadyExists userAlreadyExists) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, userAlreadyExists.getMessage());
        }
    }
}
