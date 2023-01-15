package com.foodshop.user_service.unit_tests;

import com.foodshop.user_service.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtUtilTest {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    void generateToken() {
        assertNotNull(jwtUtil.generateAccessToken(new User("username","Password",new ArrayList<>())));
    }
}