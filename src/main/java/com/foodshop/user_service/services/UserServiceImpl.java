package com.foodshop.user_service.services;

import com.foodshop.user_service.constants.SuccessMessage;
import com.foodshop.user_service.dto.AuthenticationResponseDTO;
import com.foodshop.user_service.dto.SignInUserRequestDTO;
import com.foodshop.user_service.dto.SignUpUserRequestDTO;
import com.foodshop.user_service.exceptions.UserAlreadyExistsException;
import com.foodshop.user_service.models.UserModel;
import com.foodshop.user_service.respositories.UserRepository;
import com.foodshop.user_service.utils.JwtUtil;
import com.foodshop.user_service.utils.PasswordHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;



    @Override
    public AuthenticationResponseDTO signUp(SignUpUserRequestDTO user) throws UserAlreadyExistsException{
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("User already exists");
        }
        String initialPassword = user.getPassword();
        user.setPassword(PasswordHelper.hashPassword(user.getPassword()));
        userRepository.save(user.toUser());
        AuthenticationResponseDTO responseDTO = signIn(new SignInUserRequestDTO(user.getEmail(),initialPassword));
        responseDTO.setMessage(SuccessMessage.USER_CREATED_SUCCESSFULLY);
        responseDTO.setStatusCode(HttpStatus.CREATED.value());
        return responseDTO;

    }

    @Override
    public AuthenticationResponseDTO signIn(SignInUserRequestDTO signInUserDTO) {
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

    @Override
    public UserModel getUser(String email) {
        if (!userRepository.existsByEmail(email)){
            throw new UsernameNotFoundException("User not found");
        }
        return userRepository.getUserByEmail(email);
    }


}
