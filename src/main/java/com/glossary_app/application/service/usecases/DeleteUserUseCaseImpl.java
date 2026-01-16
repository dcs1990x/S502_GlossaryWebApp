package com.glossary_app.application.service.usecases;

import com.glossary_app.application.ports.in.users.DeleteUserUseCase;
import com.glossary_app.application.ports.out.user.UserRepositoryPort;
import com.glossary_app.domain.exceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import java.util.UUID;

public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private Logger log = LoggerFactory.getLogger(getClass());

    public DeleteUserUseCaseImpl (UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public Mono<Void> deleteUser(UUID userId) {
        return userRepositoryPort.findUserById(userId)
                .flatMap(user -> userRepositoryPort.deleteUserById(userId))
                .switchIfEmpty(Mono.error(new UserNotFoundException(userId)));
    }
}