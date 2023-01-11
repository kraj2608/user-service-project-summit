package com.foodshop.user_service.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
    private final String message;

    public UserAlreadyExistsException(String msg){
        super(msg);
        this.message = msg;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
