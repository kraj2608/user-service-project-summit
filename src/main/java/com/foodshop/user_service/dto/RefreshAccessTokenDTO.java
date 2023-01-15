package com.foodshop.user_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RefreshAccessTokenDTO {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String accessToken;
    private String message;

}
