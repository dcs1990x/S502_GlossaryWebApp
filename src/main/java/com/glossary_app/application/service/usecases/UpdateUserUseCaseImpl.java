package com.glossary_app.application.service.usecases;

import com.glossary_app.application.ports.in.users.UpdateUserUseCase;
import com.glossary_app.application.ports.out.user.UpdateUserRepositoryPort;
import com.glossary_app.domain.model.User;
import reactor.core.publisher.Mono;
import java.util.UUID;

public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final UpdateUserRepositoryPort updateUserRepositoryPort;

    public UpdateUserUseCaseImpl(UpdateUserRepositoryPort updateUserRepositoryPort) {
        this.updateUserRepositoryPort = updateUserRepositoryPort;
    }

    @Override
    public Mono<User> updateNameByUserId(UUID userId) {
        return updateUserRepositoryPort.updateUserName(userId);
    }

    @Override
    public Mono<User> updateEmailByUserId(UUID userId) {
        return updateUserRepositoryPort.updateUserEmail(userId);
    }

    @Override
    public Mono<User> updatePasswordByUserId(UUID userId) {
        return updateUserRepositoryPort.updateUserPassword(userId);
    }
}