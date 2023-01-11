package com.foodshop.user_service.controllers;
import com.foodshop.user_service.models.UserDTO;
import com.foodshop.user_service.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUpUser(@RequestBody UserDTO user){
        UserDTO addedUSer = userService.saveUser(user);
        return new ResponseEntity<>(addedUSer, HttpStatus.CREATED);
    }

}
