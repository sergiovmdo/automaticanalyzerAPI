package com.aaa.automaticanalyzer.api.user.rest;

import com.aaa.automaticanalyzer.api.user.business.UserService;
import com.aaa.automaticanalyzer.api.user.domain.LoginRestInput;
import com.aaa.automaticanalyzer.model.TokenResponse;
import com.aaa.automaticanalyzer.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<TokenResponse> getUser(@RequestBody final LoginRestInput loginRestInput) {

        Optional<User> user = userService.getUserByDNI(loginRestInput.getDni());

        if (user.isPresent()){
            return ResponseEntity.ok(new TokenResponse(user.get().getToken()));
        }

        return ResponseEntity.notFound();
    }

}
