package com.foodshop.user_service.services;

import com.foodshop.user_service.dto.RegisterUserDTO;
import com.foodshop.user_service.models.User;

public interface UserService {
    User signUp(User registerUserDTO);


}
