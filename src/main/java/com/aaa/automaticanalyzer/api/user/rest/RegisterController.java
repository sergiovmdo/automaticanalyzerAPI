package com.aaa.automaticanalyzer.api.user.rest;

import com.aaa.automaticanalyzer.api.user.domain.UserRestInput;
import com.aaa.automaticanalyzer.api.user.business.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody final UserRestInput userRestInput) {
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
        userService.createUser(userRestInput);
        return ResponseEntity.ok().build();
    }

}
