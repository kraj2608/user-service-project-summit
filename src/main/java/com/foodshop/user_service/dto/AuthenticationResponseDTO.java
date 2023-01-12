package com.foodshop.user_service.dto;

import com.foodshop.user_service.models.UserModel;
import lombok.Data;
import lombok.Getter;

@Data
public class AuthenticationResponseDTO {
    private UserModel userModel;
    private String token;
}
