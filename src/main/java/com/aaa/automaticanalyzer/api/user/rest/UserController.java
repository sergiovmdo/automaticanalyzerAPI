package com.aaa.automaticanalyzer.api.user.rest;

import com.aaa.automaticanalyzer.api.user.business.UserService;
import com.aaa.automaticanalyzer.api.user.domain.LanguageRestInput;
import com.aaa.automaticanalyzer.api.user.domain.PasswordRestInput;
import com.aaa.automaticanalyzer.common.AuthAwareRestController;
import com.aaa.automaticanalyzer.model.FCMToken;
import com.aaa.automaticanalyzer.model.SimplifiedUser;
import com.aaa.automaticanalyzer.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public SimplifiedUser getUser(final User user){
        //TODO: Map to another objet that does not have the password
        return SimplifiedUser.fromUser(user);
    }

    @PutMapping
    public ResponseEntity<Void> changePassword(@RequestBody final PasswordRestInput password, final User user){
       return userService.changePassword(password, user.getDni());
    }

    @PutMapping("/fcmtoken")
    public ResponseEntity<Void> insertFCMToken(@RequestBody final FCMToken token, final User user){
        return userService.setFCMToken(token, user);
    }

    @PutMapping("/language")
    public ResponseEntity<Void> changeLanguage(@RequestBody final LanguageRestInput languageRestInput, final User user){
        return userService.changeLanguage(languageRestInput, user.getDni());
    }
}
