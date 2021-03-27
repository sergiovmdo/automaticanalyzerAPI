package com.aaa.automaticanalyzer.api.user.rest;

import com.aaa.automaticanalyzer.api.user.business.UserService;
import com.aaa.automaticanalyzer.api.user.domain.LoginRestInput;
import com.aaa.automaticanalyzer.model.TokenResponse;
import com.aaa.automaticanalyzer.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<TokenResponse> getUserToken(@RequestBody final LoginRestInput loginRestInput) {

        Optional<User> user = userService.getUserByDNI(loginRestInput.getDni());

        if (user.isPresent()) {
            String password = userService.hashPassword(loginRestInput.getPassword());
            if (password.equals(user.get().getPassword()))
                return ResponseEntity.ok(new TokenResponse(user.get().getToken()));
        }

        return ResponseEntity.notFound().build();
    }

}
