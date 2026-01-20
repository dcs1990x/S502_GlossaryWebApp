package com.glossary_app.application.service.userUseCases;

import com.glossary_app.application.ports.in.users.UpdateUserUseCase;
import com.glossary_app.application.ports.out.UserRepositoryPort;
import com.glossary_app.domain.exceptions.UserNotFoundException;
import com.glossary_app.domain.model.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public UpdateUserUseCaseImpl(UserRepositoryPort updateUserRepositoryPort) {
        this.userRepositoryPort = updateUserRepositoryPort;
    }

    @Override
    public Mono<User> updateNameByUserId(UUID userId, String newName) {
        return userRepositoryPort.findUserById(userId)
                .switchIfEmpty(Mono.error(new UserNotFoundException(userId)))
                .map(user -> user.withName(newName))
                .flatMap(userRepositoryPort::saveUser);
    }

    @Override
    public Mono<User> updateEmailByUserId(UUID userId, String newEmail) {
        return userRepositoryPort.findUserById(userId)
                .switchIfEmpty(Mono.error(new UserNotFoundException(userId)))
                .map(user -> user.withName(newEmail))
                .flatMap(userRepositoryPort::saveUser);
    }

    @Override
    public Mono<User> updatePasswordByUserId(UUID userId, String newPassword) {
        return userRepositoryPort.findUserById(userId)
                .switchIfEmpty(Mono.error(new UserNotFoundException(userId)))
                .map(user -> user.withName(newPassword))
                .flatMap(userRepositoryPort::saveUser);
    }
}