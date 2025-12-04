package com.glossary_app.exceptions;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(){
        super("No users were found.");
    }
    public UserNotFoundException(UUID userId) {
        super("User with ID " + userId + " could not be found.");
    }
}