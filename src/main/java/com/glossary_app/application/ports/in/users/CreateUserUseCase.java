package com.glossary_app.application.ports.in.users;

import com.glossary_app.domain.dtos.request.CreateUserRequestDTO;
import com.glossary_app.domain.model.User;
import reactor.core.publisher.Mono;

public interface CreateUserUseCase {
    Mono<CreateUserRequestDTO> createNewUser(User user);
}