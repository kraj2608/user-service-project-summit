package com.foodshop.user_service.services;

import com.foodshop.user_service.exceptions.UserAlreadyExistsException;
import com.foodshop.user_service.models.UserModel;
import com.foodshop.user_service.respositories.UserRepository;
import com.foodshop.user_service.utils.PasswordHelper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserModel signUp(UserModel user) throws UserAlreadyExistsException{
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("User already exists");
        }
        user.setPassword(PasswordHelper.hashPassword(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserModel getUser(String email) {
        return userRepository.getUserByEmail(email);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!userRepository.existsByEmail(username)) {
            throw new UsernameNotFoundException("Email or password is invalid");
        }
        UserModel user = userRepository.getUserByEmail(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(user.getRole());
        return new User(user.getEmail(), user.getPassword(), authorities);
    }
}
