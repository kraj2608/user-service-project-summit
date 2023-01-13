package com.foodshop.user_service.utils;

import com.foodshop.user_service.exceptions.InvalidArgumentException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHelper {

    private PasswordHelper(){}

    public static String hashPassword(String password) throws InvalidArgumentException {
        if(password == null){
            throw new InvalidArgumentException("Invalid password");
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
        return bCryptPasswordEncoder.encode(password);
    }
}
