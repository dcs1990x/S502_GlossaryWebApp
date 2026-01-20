package com.glossary_app.application.ports.in.users;

import reactor.core.publisher.Mono;
import java.util.UUID;

public interface DeleteUserUseCase {
    Mono<Void> deleteUser(UUID userId);
}