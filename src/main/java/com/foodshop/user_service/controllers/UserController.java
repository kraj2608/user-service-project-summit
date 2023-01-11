package com.foodshop.user_service.controllers;
import com.foodshop.user_service.dto.RegisterUserDTO;
import com.foodshop.user_service.models.User;
import com.foodshop.user_service.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signUpUser(@Valid @RequestBody RegisterUserDTO user){
        User addedUSer = userService.saveUser(user.toUser());
        return new ResponseEntity<>(addedUSer, HttpStatus.CREATED);
    }

}
