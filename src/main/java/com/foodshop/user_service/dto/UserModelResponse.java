package com.foodshop.user_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.foodshop.user_service.models.UserModel;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserModelResponse {

    private UserModel user;

    @JsonProperty("status_code")
    private int statusCode;

    private String message;
}
