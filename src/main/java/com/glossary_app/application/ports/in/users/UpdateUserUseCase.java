package com.glossary_app.application.ports.in.users;

import com.glossary_app.domain.model.User;
import reactor.core.publisher.Mono;

public interface UpdateUserUseCase {
    Mono<User> updateName(User user);
    Mono<User> updateEmail(User user);
    Mono<User> updatePassword(User user);
}