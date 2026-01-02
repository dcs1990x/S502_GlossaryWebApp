package com.glossary_app.domain.exceptions;

public class InvalidActionException extends RuntimeException {
    public InvalidActionException() {
        super("Invalid action.");
    }
}