package com.foodshop.user_service.services;

import com.foodshop.user_service.exceptions.UserAlreadyExistsException;
import com.foodshop.user_service.models.UserDTO;
import com.foodshop.user_service.respositories.UserRepository;
import com.foodshop.user_service.utils.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        boolean exists = userRepository.existsByEmail(userDTO.getEmail());
        if(exists){
            throw new UserAlreadyExistsException("User already exists");
        }
        userDTO.setPassword(PasswordHelper.hashPassword(userDTO.getPassword()));
        return userRepository.save(userDTO);
    }
}
