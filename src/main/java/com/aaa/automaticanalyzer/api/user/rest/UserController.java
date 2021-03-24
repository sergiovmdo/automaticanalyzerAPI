package com.aaa.automaticanalyzer.api.user.rest;

import com.aaa.automaticanalyzer.common.AuthAwareRestController;
import com.aaa.automaticanalyzer.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@AuthAwareRestController
public class UserController {

    @GetMapping
    public User getUser(final User user){
        //TODO: Map to another objet that does not have the password
        return user;
    }
}
