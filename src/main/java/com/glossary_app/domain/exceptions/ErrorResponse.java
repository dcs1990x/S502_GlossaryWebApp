package com.glossary_app.domain.exceptions;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private String message;

    public ErrorResponse(String message){
        this.message = message;
    }
}