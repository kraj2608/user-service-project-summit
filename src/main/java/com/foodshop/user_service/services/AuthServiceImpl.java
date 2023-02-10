package com.foodshop.user_service.services;

import com.foodshop.user_service.constants.SuccessMessage;
import com.foodshop.user_service.dto.AuthenticationResponseDTO;
import com.foodshop.user_service.dto.RefreshAccessTokenDTO;
import com.foodshop.user_service.dto.SignInUserRequestDTO;
import com.foodshop.user_service.models.UserModel;
import com.foodshop.user_service.respositories.UserRepository;
import com.foodshop.user_service.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService{

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

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

    @Override
    public AuthenticationResponseDTO signIn(SignInUserRequestDTO signInUserDTO) throws UsernameNotFoundException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInUserDTO.getEmail(),
                        signInUserDTO.getPassword()
                )
        );
        UserModel userModel = userRepository.getUserByEmail(signInUserDTO.getEmail());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(userModel.getRole());
        String jwtAccessToken = jwtUtil.generateAccessToken(new User(userModel.getEmail(), userModel.getPassword(), authorities));
        String jwtRefreshToken = jwtUtil.generateRefreshToken(new User(userModel.getEmail(), userModel.getPassword(), authorities));
        return AuthenticationResponseDTO.builder()
                .user(userModel)
                .accessToken(jwtAccessToken)
                .refreshToken(jwtRefreshToken)
                .message(SuccessMessage.USER_SIGNIN_SUCCESSFULLY)
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}
