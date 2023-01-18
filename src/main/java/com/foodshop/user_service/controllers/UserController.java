package com.foodshop.user_service.controllers;

import com.foodshop.user_service.dto.AuthenticationResponseDTO;
import com.foodshop.user_service.dto.SignInUserRequestDTO;
import com.foodshop.user_service.dto.SignUpUserRequestDTO;
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
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponseDTO> signUpUser(@Valid @RequestBody SignUpUserRequestDTO user) {
        HttpHeaders responseHeaders = new HttpHeaders();
        AuthenticationResponseDTO response = userService.signUp(user);
        responseHeaders.add(HttpHeaders.AUTHORIZATION,response.getAccessToken());
        return new ResponseEntity<>(response,responseHeaders,HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponseDTO> signInUser(@Valid @RequestBody SignInUserRequestDTO user) throws BadCredentialsException {
        HttpHeaders responseHeaders = new HttpHeaders();
        AuthenticationResponseDTO response = userService.signIn(user);
        responseHeaders.add(HttpHeaders.AUTHORIZATION,response.getAccessToken());
        return new ResponseEntity<>(response,responseHeaders,HttpStatus.OK);
    }



}
