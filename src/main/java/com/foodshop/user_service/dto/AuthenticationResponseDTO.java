package com.foodshop.user_service.dto;

import com.foodshop.user_service.models.UserModel;
import lombok.Data;

@Data
public class AuthenticationResponseDTO {
    private UserModel userModel;
    private String token;
}
