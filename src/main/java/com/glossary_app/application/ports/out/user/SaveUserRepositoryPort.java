package com.glossary_app.application.ports.out.user;

import com.glossary_app.domain.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface SaveUserRepositoryPort {
    Mono<User> saveUser(User user);
}
