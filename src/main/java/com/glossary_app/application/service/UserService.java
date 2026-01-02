package com.glossary_app.application.service;

import com.glossary_app.application.ports.in.users.CreateUserUseCase;
import com.glossary_app.application.ports.in.users.DeleteUserUseCase;
import com.glossary_app.application.ports.in.users.RetrieveUserUseCase;
import com.glossary_app.application.ports.in.users.UpdateUserUseCase;
import com.glossary_app.domain.dtos.request.UpdateEmailRequestDTO;
import com.glossary_app.domain.dtos.request.UpdatePasswordRequestDTO;
import com.glossary_app.domain.dtos.request.UpdateUserNameRequestDTO;
import com.glossary_app.domain.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

public class UserService implements CreateUserUseCase, RetrieveUserUseCase, UpdateUserUseCase, DeleteUserUseCase {

    private final CreateUserUseCase createUserUseCase;
    private final RetrieveUserUseCase retrieveUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    public UserService(CreateUserUseCase createUserUseCase, RetrieveUserUseCase retrieveUserUseCase, UpdateUserUseCase updateUserUseCase, DeleteUserUseCase deleteUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.retrieveUserUseCase = retrieveUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
    }

    @Override
    public Mono<User> createNewUser(User user) {
        return createUserUseCase.createNewUser(user);
    }

    @Override
    public User getUserById(UUID userId) {
        return retrieveUserUseCase.getUserById(userId);
    }

    @Override
    public Flux<User> getAllUsers() {
        return retrieveUserUseCase.getAllUsers();
    }

    @Override
    public User updateNameByUserId(UUID userId, UpdateUserNameRequestDTO updateUserNameRequestDTO) {
        return updateUserUseCase.updateNameByUserId(userId, updateUserNameRequestDTO);
    }

    @Override
    public User updateEmailByUserId(UUID userId, UpdateEmailRequestDTO updateEmailRequestDTO) {
        return updateUserUseCase.updateEmailByUserId(userId, updateEmailRequestDTO);
    }

    @Override
    public User updatePasswordByUserId(UUID userId, UpdatePasswordRequestDTO updatePasswordRequestDTO) {
        return updateUserUseCase.updatePasswordByUserId(userId, updatePasswordRequestDTO);
    }

    @Override
    public void deleteUser(UUID userId) {
        deleteUserUseCase.deleteUser(userId);
    }
}