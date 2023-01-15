package com.foodshop.user_service.controllers;

import com.foodshop.user_service.dto.AuthenticationResponseDTO;
import com.foodshop.user_service.dto.SignInUserDTO;
import com.foodshop.user_service.dto.SignUpUserDTO;
import com.foodshop.user_service.models.UserModel;
import com.foodshop.user_service.services.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserModel> signUpUser(@Valid @RequestBody SignUpUserDTO user) {
        UserModel addedUSer = userService.signUp(user.toUser());
        return new ResponseEntity<>(addedUSer, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponseDTO> signInUser(@Valid @RequestBody SignInUserDTO user) throws BadCredentialsException {
        HttpHeaders responseHeaders = new HttpHeaders();
        AuthenticationResponseDTO response = userService.signIn(user);
        responseHeaders.add(HttpHeaders.AUTHORIZATION,response.getAccessToken());
        return new ResponseEntity<>(userService.signIn(user),responseHeaders,HttpStatus.OK);
    }



}
