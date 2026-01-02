package com.glossary_app.application.ports.in.users;

import com.glossary_app.domain.model.User;
import reactor.core.publisher.Flux;
import java.util.UUID;

public interface RetrieveUserUseCase {
    User getUserById(UUID userId);
    Flux<User> getAllUsers();
}