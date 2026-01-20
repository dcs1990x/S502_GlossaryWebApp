package com.glossary_app.application.ports.out;

import com.glossary_app.domain.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface UserRepositoryPort {
    Mono<User> saveUser(User user);
    Mono<User> findUserById(UUID userId);
    Mono<User> findUserByEmail(String email);
    Flux<User> findAllUsers();
    Mono<Void> deleteUserById(UUID userId);
}