package com.foodshop.user_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RefreshAccessTokenDTO {

    @JsonProperty("access_token")
    private String accessToken;
    private String message;

}
