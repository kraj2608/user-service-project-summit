package com.foodshop.user_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.foodshop.user_service.models.UserModel;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthenticationResponseDTO {
    private UserModel user;
    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("access_time")
    private String accessToken;

    private String message;

    @JsonProperty("status_code")
    private int statusCode;

}
