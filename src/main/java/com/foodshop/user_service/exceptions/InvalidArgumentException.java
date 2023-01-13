package com.foodshop.user_service.exceptions;

public class InvalidArgumentException extends RuntimeException{
    private final String message;


    public InvalidArgumentException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
