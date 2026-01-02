package com.glossary_app.domain.exceptions;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(){
        super("No users were found.");
    }
    public UserNotFoundException(UUID userId) {
        super("CreateUserRequestDTO with ID " + userId + " could not be found.");
    }
}