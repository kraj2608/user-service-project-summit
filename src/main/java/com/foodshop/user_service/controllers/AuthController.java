package com.foodshop.user_service.controllers;

import com.foodshop.user_service.dto.*;
import com.foodshop.user_service.services.IAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;


    @GetMapping("")
    public ResponseEntity<RequestAccessMessageDTO> check(){
        return new ResponseEntity<>(
                RequestAccessMessageDTO
                        .builder()
                        .message("Login success")
                        .statusCode(HttpStatus.OK.value())
                        .build(), HttpStatus.OK
        );
    }

    @PostMapping("")
    public ResponseEntity<AuthenticationResponseDTO> signIn(@Valid @RequestBody SignInUserRequestDTO
                                                                        signInUserRequestDTO){
        HttpHeaders responseHeaders = new HttpHeaders();
        AuthenticationResponseDTO responseDTO = authService.signIn(signInUserRequestDTO);
        responseHeaders.add(HttpHeaders.AUTHORIZATION,responseDTO.getAccessToken());
        return new ResponseEntity<>(
                responseDTO,
                responseHeaders,
                HttpStatus.OK
        );
    }

    @GetMapping("/refresh")
    public ResponseEntity<RefreshAccessTokenDTO> requestAccessToken(){
        HttpHeaders responseHeaders = new HttpHeaders();
        RefreshAccessTokenDTO refreshAccessTokenDTO = authService.refreshToken();
        responseHeaders.add(HttpHeaders.AUTHORIZATION,refreshAccessTokenDTO.getAccessToken());
        return new ResponseEntity<>(
                refreshAccessTokenDTO,
                responseHeaders,
                HttpStatus.OK
        );
    }
}
