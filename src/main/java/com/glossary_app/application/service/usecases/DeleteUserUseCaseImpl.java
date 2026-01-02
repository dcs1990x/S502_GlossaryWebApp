package com.glossary_app.application.service.usecases;

import com.glossary_app.application.ports.in.users.DeleteUserUseCase;
import com.glossary_app.application.ports.out.UserRepositoryPort;
import java.util.UUID;

public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public DeleteUserUseCaseImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public void deleteUser(UUID userId) {
        userRepositoryPort.deleteUserById(userId);
    }
}