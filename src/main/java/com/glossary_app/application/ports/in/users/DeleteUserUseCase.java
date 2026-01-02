package com.glossary_app.application.ports.in.users;

import java.util.UUID;

public interface DeleteUserUseCase {
    void deleteUser(UUID userId);
}