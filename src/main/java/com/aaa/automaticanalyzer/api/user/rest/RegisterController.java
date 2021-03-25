package com.aaa.automaticanalyzer.api.user.rest;

import com.aaa.automaticanalyzer.api.user.domain.UserRestInput;
import com.aaa.automaticanalyzer.api.user.business.UserService;
import com.aaa.automaticanalyzer.model.TokenResponse;
import com.aaa.automaticanalyzer.model.User;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
@AllArgsConstructor
public class RegisterController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<TokenResponse> createUser(@RequestBody final UserRestInput userRestInput) {
        User user = userService.createUser(userRestInput);
        return ResponseEntity.ok(new TokenResponse(user.getToken()));
    }

}
