package com.foodshop.user_service.services;

import com.foodshop.user_service.dto.AuthenticationResponseDTO;
import com.foodshop.user_service.dto.RefreshAccessTokenDTO;
import com.foodshop.user_service.dto.SignInUserRequestDTO;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IAuthService {
    RefreshAccessTokenDTO refreshToken();

    AuthenticationResponseDTO signIn(SignInUserRequestDTO userRequestDTO) throws UsernameNotFoundException;
}
