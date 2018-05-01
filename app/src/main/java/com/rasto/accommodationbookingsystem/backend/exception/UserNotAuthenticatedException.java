package com.rasto.accommodationbookingsystem.backend.exception;

public class UserNotAuthenticatedException extends Exception {

    public UserNotAuthenticatedException(String message) {
        super(message);
    }
}
