package com.glossary_app.application.service.usecases;

import com.glossary_app.application.ports.in.users.UpdateUserUseCase;
import com.glossary_app.application.ports.out.UserRepositoryPort;
import com.glossary_app.domain.model.User;
import java.util.UUID;

public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public UpdateUserUseCaseImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User updateNameByUserId(UUID userId, User user) {
        return userRepositoryPort.updateUserName();
    }

    @Override
    public User updateEmailByUserId(UUID userId, User user) {
        return userRepositoryPort.updateUserEmail();
    }

    @Override
    public User updatePasswordByUserId(UUID userId, User user) {
        return userRepositoryPort.updateUserPassword();
    }
}