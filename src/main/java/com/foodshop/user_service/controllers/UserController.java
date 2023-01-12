package com.foodshop.user_service.controllers;

import com.foodshop.user_service.dto.SignInUserDTO;
import com.foodshop.user_service.dto.SignUpUserDTO;
import com.foodshop.user_service.models.UserModel;
import com.foodshop.user_service.services.IUserService;
import com.foodshop.user_service.utils.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/auth")
public class UserController {

    private final IUserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    public UserController(IUserService userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserModel> signUpUser(@Valid @RequestBody SignUpUserDTO user) {
        UserModel addedUSer = userService.signUp(user.toUser());
        return new ResponseEntity<>(addedUSer, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signInUser(@Valid @RequestBody SignInUserDTO user) throws BadCredentialsException {
        Authentication authenticate = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                user.getEmail(), user.getPassword()
                        )
                );
        return ResponseEntity.ok()
                .header(
                        HttpHeaders.AUTHORIZATION,
                        jwtUtil.generateToken(userService.loadUserByUsername(user.getEmail()))
                )
                .body(userService.getUser(user.getEmail()));

    }

}
