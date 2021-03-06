package com.aaa.automaticanalyzer.api.user.rest;

import com.aaa.automaticanalyzer.api.user.business.UserService;
import com.aaa.automaticanalyzer.api.user.domain.LoginRestInput;
import com.aaa.automaticanalyzer.exceptions.InvalidPassword;
import com.aaa.automaticanalyzer.exceptions.UserNotFound;
import com.aaa.automaticanalyzer.model.TokenResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {

    private final UserService userService;

    @PostMapping
    public TokenResponse getUserToken(@RequestBody final LoginRestInput loginRestInput) {
        try {
            return new TokenResponse(userService.login(loginRestInput));
        } catch (InvalidPassword ip) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ip.getMessage());
        } catch (UserNotFound unf) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, unf.getMessage());
        }
    }
}
