package com.glossary_app.application.ports.in.users;

import com.glossary_app.domain.model.User;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface UpdateUserUseCase {
    Mono<User> updateNameByUserId(UUID userId, String name);
    Mono<User> updateEmailByUserId(UUID userId, String email);
    Mono<User> updatePasswordByUserId(UUID userId, String password);
}