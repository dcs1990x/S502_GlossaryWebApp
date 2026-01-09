package com.glossary_app.application.service.usecases;

import com.glossary_app.application.ports.in.users.CreateUserUseCase;
import com.glossary_app.application.ports.out.user.SaveUserRepositoryPort;
import com.glossary_app.domain.UserValidationService;
import com.glossary_app.domain.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import static reactor.netty.http.HttpConnectionLiveness.log;

@Service
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final SaveUserRepositoryPort saveUserRepositoryPort;
    private final UserValidationService validationService;

    @Override
    public Mono<User> createNewUser(User user) {
        return Mono.just(user)
                .flatMap(req -> validationService.validateEmailNotExists(req.getEmail())
                        .thenReturn(req))
                .map(req -> User.createNewUser(req.getEmail(), req.getPassword()))
                .flatMap(saveUserRepositoryPort::saveUser)
                .doOnSuccess(createdUser ->
                        log.info("CreateUserRequestDTO created successfully: {}", createdUser.getUserId()))
                .doOnError(error ->
                        log.error("Error creating user: {}", error.getMessage()));
    }
}