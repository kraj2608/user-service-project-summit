package com.foodshop.user_service.enums;

import org.springframework.security.core.GrantedAuthority;

public enum USEROLE implements GrantedAuthority {
    CUSTOMER("CUSTOMER"),
    ADMIN("ADMIN");



    private String role;

    USEROLE(String role){
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
