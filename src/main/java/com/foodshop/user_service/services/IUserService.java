package com.foodshop.user_service.services;

import com.foodshop.user_service.dto.SignUpUserRequestDTO;
import com.foodshop.user_service.dto.UserModelResponse;
import com.foodshop.user_service.exceptions.UserAlreadyExistsException;

public interface IUserService {
    UserModelResponse getUser(String email);
    UserModelResponse addUser(SignUpUserRequestDTO registerUserDTO)  throws UserAlreadyExistsException;

}
