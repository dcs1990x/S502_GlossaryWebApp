package com.glossary_app.application.service.usecases;

import com.glossary_app.application.ports.in.users.CreateUserUseCase;
import com.glossary_app.application.ports.out.UserRepositoryPort;
import com.glossary_app.domain.UserValidationService;
import com.glossary_app.infrastructure.mappers.DTOEntityMapper;
import com.glossary_app.infrastructure.mappers.ModelEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import static reactor.netty.http.HttpConnectionLiveness.log;

@Service
@RequiredArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final ModelEntityMapper modelEntityMapper;
    private final DTOEntityMapper dtoEntityMapper;
    private final UserValidationService validationService;

    @Override
    public Mono<User> createNewUser(User user) {
        return Mono.just(user)
                .flatMap(req -> validationService.validateEmailNotExists(req.email())
                        .thenReturn(req))
                .map(req -> com.glossary_app.domain.model.User.createNewUser(req.email(), req.password()))
                .flatMap(userRepositoryPort::saveUser)
                .doOnSuccess(createdUser ->
                        log.info("CreateUserRequestDTO created successfully: {}", createdUser.getUserId()))
                .doOnError(error ->
                        log.error("Error creating user: {}", error.getMessage()));
    }
}