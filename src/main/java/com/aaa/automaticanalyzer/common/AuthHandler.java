package com.aaa.automaticanalyzer.common;

import com.aaa.automaticanalyzer.api.user.business.UserService;
import com.aaa.automaticanalyzer.api.user.rest.mapping.UserMapper;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.server.ResponseStatusException;

/**
 * Extracts the user from the Authorization HTTP header
 */
@ControllerAdvice(annotations = AuthAwareRestController.class)
@RequiredArgsConstructor
public class AuthHandler
{
    final UserService userService;

    @ModelAttribute
    public void validateToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, final Model model)
    {
        try {
            userService.validateToken(token).ifPresent(model::addAttribute);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}
