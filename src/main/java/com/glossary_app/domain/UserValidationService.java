package com.glossary_app.domain;

import com.glossary_app.application.ports.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserValidationService {

    private final UserRepositoryPort userRepositoryPort;

    public Mono<Void> validateEmailNotExists(String email) {
        return userRepositoryPort.findUserByEmail(email)
                .flatMap(user -> Mono.<Void>error(
                        new IllegalArgumentException("Email already exists: " + email)
                ))
                .switchIfEmpty(Mono.empty());
    }
}