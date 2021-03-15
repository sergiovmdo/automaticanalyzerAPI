package com.aaa.automaticanalyzer.controllers;

import com.aaa.automaticanalyzer.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createPock(@RequestBody final CreatePockRestInput pockRestInput, final User user) {
//        try
//        {
//            return PockMappers.mapPockToPockRestOutput(pocksService.createPock(pockRestInput, user));
//        }
//        catch (CategoryNotValid categoryNotValid)
//        {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Category %d is not valid", pockRestInput.getCategory()));
//        }
//        catch (PockNotFound pockNotFound)
//        {
//            // Let's just treat this as an internal server error as it should never enter here
//            // A log in case we enter
//            log.error("Returning a freshly created pock [{}] returned PockNotFound", pockNotFound.getId());
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
        return ResponseEntity.ok().build();
    }
}
