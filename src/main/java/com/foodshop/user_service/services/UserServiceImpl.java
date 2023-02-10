package com.foodshop.user_service.services;

import com.foodshop.user_service.dto.SignUpUserRequestDTO;
import com.foodshop.user_service.dto.UserModelResponse;
import com.foodshop.user_service.exceptions.UserAlreadyExistsException;
import com.foodshop.user_service.models.UserModel;
import com.foodshop.user_service.respositories.UserRepository;
import com.foodshop.user_service.utils.JwtUtil;
import com.foodshop.user_service.utils.PasswordHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    @Override
    public UserModelResponse getUser(String email) {
        if (!userRepository.existsByEmail(email)){
            throw new UsernameNotFoundException("User not found");
        }
        UserModel userModel = userRepository.getUserByEmail(email);
        return UserModelResponse.builder()
                .user(userModel)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @Override
    public UserModelResponse addUser(SignUpUserRequestDTO registerUserDTO) throws UserAlreadyExistsException {
        if (userRepository.existsByEmail(registerUserDTO.getEmail())) {
            throw new UserAlreadyExistsException("User already exists");
        }
        registerUserDTO.setPassword(PasswordHelper.hashPassword(registerUserDTO.getPassword()));
        return UserModelResponse
                .builder()
                .user(userRepository.save(registerUserDTO.toUser()))
                .statusCode(HttpStatus.CREATED.value())
                .message("User account created successfully!")
                .build();

    }


}
