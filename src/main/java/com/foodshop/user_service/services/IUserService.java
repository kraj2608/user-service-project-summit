package com.foodshop.user_service.services;

import com.foodshop.user_service.exceptions.UserAlreadyExistsException;
import com.foodshop.user_service.models.UserModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserService extends UserDetailsService {
    UserModel signUp(UserModel registerUserDTO)  throws UserAlreadyExistsException;
    UserModel getUser(String email);
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
