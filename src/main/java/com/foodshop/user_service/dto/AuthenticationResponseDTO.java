package com.foodshop.user_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.foodshop.user_service.models.UserModel;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AuthenticationResponseDTO {
    private UserModel user;
    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String accessToken;
}
