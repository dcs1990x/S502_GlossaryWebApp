package com.glossary_app.exceptions;

public class TermNotFoundException extends RuntimeException {
    public TermNotFoundException(Long gameId) {
        super("Game with the ID " + gameId + " could not be found");
    }

    public TermNotFoundException(){super("There are no created games");}
}