package com.foodshop.user_service.controllers;

import com.foodshop.user_service.dto.SignUpUserRequestDTO;
import com.foodshop.user_service.dto.UserModelResponse;
import com.foodshop.user_service.services.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping("")
    public ResponseEntity<UserModelResponse> signUpUser(@Valid @RequestBody SignUpUserRequestDTO user) {
        return new ResponseEntity<>(userService.addUser(user),HttpStatus.OK);
    }

    @GetMapping("/{email}") // Get the user
    public ResponseEntity<UserModelResponse> getUser(@PathVariable String email)
            throws BadCredentialsException {
        return new ResponseEntity<>(userService.getUser(email),HttpStatus.OK);
    }



}
