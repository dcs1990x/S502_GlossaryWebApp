package com.glossary_app.exceptions;

public class InvalidActionException extends RuntimeException {
    public InvalidActionException() {
        super("Invalid action.");
    }
}