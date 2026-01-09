package com.glossary_app.application.ports.out.user;

import reactor.core.publisher.Mono;
import java.util.UUID;

public interface DeleteUserRepositoryPort {
    Mono<Void> deleteUserById(UUID userId);
}