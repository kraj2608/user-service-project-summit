package com.foodshop.user_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.foodshop.user_service.models.UserModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidateResponseDTO {
    @JsonProperty("status_code")
    private int statusCode;

    private String message;

    private UserModel user;

}
