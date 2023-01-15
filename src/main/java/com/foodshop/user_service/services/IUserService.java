package com.foodshop.user_service.services;

import com.foodshop.user_service.dto.AuthenticationResponseDTO;
import com.foodshop.user_service.dto.SignInUserDTO;
import com.foodshop.user_service.exceptions.UserAlreadyExistsException;
import com.foodshop.user_service.models.UserModel;

public interface IUserService {
    UserModel signUp(UserModel registerUserDTO)  throws UserAlreadyExistsException;
    UserModel getUser(String email);
    AuthenticationResponseDTO signIn(SignInUserDTO signInUserDTO);

}
