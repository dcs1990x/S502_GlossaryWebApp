package com.glossary_app.service;

import com.glossary_app.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository playerRepository;

    public UserService(UserRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
}