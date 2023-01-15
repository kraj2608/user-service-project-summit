package com.foodshop.user_service.controllers;

import com.foodshop.user_service.dto.RefreshAccessTokenDTO;
import com.foodshop.user_service.dto.ValidateResponseDTO;
import com.foodshop.user_service.services.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;

    @GetMapping("/validate")
    public ResponseEntity<ValidateResponseDTO> validateUser() {
        return new ResponseEntity<>(
                ValidateResponseDTO
                        .builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("User validation success")
                        .build(),
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
