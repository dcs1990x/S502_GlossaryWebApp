package com.glossary_app.domain.exceptions;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(UUID userId) {
        super("User with ID " + userId + " could not be found.");
    }
}