package com.foodshop.user_service.services;

import com.foodshop.user_service.exceptions.UserAlreadyExistsException;
import com.foodshop.user_service.dto.RegisterUserDTO;
import com.foodshop.user_service.models.User;
import com.foodshop.user_service.respositories.UserRepository;
import com.foodshop.user_service.utils.PasswordHelper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public User signUp(User user) {
        boolean exists = userRepository.existsByEmail(user.getEmail());
        if(exists){
            throw new UserAlreadyExistsException("User already exists");
        }
        user.setPassword(PasswordHelper.hashPassword(user.getPassword()));
        return userRepository.save(user);
    }
}
