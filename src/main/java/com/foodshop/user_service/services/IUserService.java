package com.foodshop.user_service.services;

import com.foodshop.user_service.dto.AuthenticationResponseDTO;
import com.foodshop.user_service.dto.SignInUserRequestDTO;
import com.foodshop.user_service.dto.SignUpUserRequestDTO;
import com.foodshop.user_service.exceptions.UserAlreadyExistsException;
import com.foodshop.user_service.models.UserModel;

public interface IUserService {
    AuthenticationResponseDTO signUp(SignUpUserRequestDTO registerUserDTO)  throws UserAlreadyExistsException;
    AuthenticationResponseDTO signIn(SignInUserRequestDTO signInUserDTO);

    UserModel getUser(String email);

}
