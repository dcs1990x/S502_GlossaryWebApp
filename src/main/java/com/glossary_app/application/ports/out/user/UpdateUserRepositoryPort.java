package com.glossary_app.application.ports.out.user;

import com.glossary_app.domain.model.User;
import reactor.core.publisher.Mono;

public interface UpdateUserRepositoryPort {
    Mono<User> updateUserName(User user);
    Mono<User> updateUserEmail(User user);
    Mono<User> updateUserPassword(User user);
}