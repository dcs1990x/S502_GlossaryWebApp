package com.glossary_app.application.ports.out.user;

import com.glossary_app.domain.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface FindUserRepositoryPort {
    Mono<User> findUserById(UUID userId);
    Mono<User> findByEmail(String email);
    Flux<User> findAllUsers();
    //Mono<Boolean> existsByEmail(String email);
}