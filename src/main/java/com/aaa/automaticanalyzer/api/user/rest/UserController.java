package com.aaa.automaticanalyzer.api.user.rest;

import com.aaa.automaticanalyzer.api.user.business.UserService;
import com.aaa.automaticanalyzer.api.user.domain.PasswordRestInput;
import com.aaa.automaticanalyzer.common.AuthAwareRestController;
import com.aaa.automaticanalyzer.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
@AuthAwareRestController
public class UserController {

    private final UserService userService;

    @GetMapping
    public User getUser(final User user){
        //TODO: Map to another objet that does not have the password
        return user;
    }

    @PutMapping
    public void changePassword(@RequestBody final PasswordRestInput password, final User user){
        userService.changePassword(password, user.getDni());
    }
}
