package com.foodshop.user_service.services;

import com.foodshop.user_service.dto.RefreshAccessTokenDTO;
import com.foodshop.user_service.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService{

    private final JwtUtil jwtUtil;

    @Override
    public RefreshAccessTokenDTO refreshToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        String accessToken = jwtUtil.generateAccessToken(user);
        return RefreshAccessTokenDTO
                .builder()
                .accessToken(accessToken)
                .message("New access token generated")
                .build();
    }
}
