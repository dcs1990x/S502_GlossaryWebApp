package com.glossary_app.application.ports.in.users;

import com.glossary_app.domain.dtos.query.UserWithCollections;
import com.glossary_app.domain.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface RetrieveUserUseCase {
    Mono<User> getUserById(UUID userId);
    Mono<UserWithCollections>  getUserWithCollections(UUID userId);
    Flux<User> getAllUsers();
}