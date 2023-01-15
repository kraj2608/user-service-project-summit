package com.foodshop.user_service.controllers;

import com.foodshop.user_service.dto.ValidateResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
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
}
