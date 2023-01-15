package com.foodshop.user_service.services;

import com.foodshop.user_service.dto.RefreshAccessTokenDTO;

public interface IAuthService {
    RefreshAccessTokenDTO refreshToken();
}
