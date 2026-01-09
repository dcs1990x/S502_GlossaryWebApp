package com.glossary_app.application.service.usecases;

import com.glossary_app.application.ports.in.users.DeleteUserUseCase;
import com.glossary_app.application.ports.out.user.DeleteUserRepositoryPort;
import java.util.UUID;

public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private final DeleteUserRepositoryPort deleteUserRepositoryPort;

    public DeleteUserUseCaseImpl (DeleteUserRepositoryPort deleteUserRepositoryPort) {
        this.deleteUserRepositoryPort = deleteUserRepositoryPort;
    }

    @Override
    public void deleteUser(UUID userId) {
        deleteUserRepositoryPort.deleteUserById(userId);
    }
}