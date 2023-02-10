package com.foodshop.user_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RequestAccessMessageDTO {
    private final String message;

    @JsonProperty("status_code")
    private final int statusCode;
}
