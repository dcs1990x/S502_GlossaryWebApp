package com.glossary_app.infrastructure.controller;

import com.glossary_app.application.service.usecases.CreateUserUseCaseImpl;
import com.glossary_app.application.service.usecases.DeleteUserUseCaseImpl;
import com.glossary_app.application.service.usecases.RetrieveUserUseCaseImpl;
import com.glossary_app.application.service.usecases.UpdateUserUseCaseImpl;
import com.glossary_app.domain.dtos.request.*;
import com.glossary_app.domain.dtos.response.UserResponseDTO;
import com.glossary_app.domain.dtos.response.UserWithCollectionsResponseDTO;
import com.glossary_app.domain.model.User;
import com.glossary_app.infrastructure.mappers.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "CRUD API for Users in Glossary Study App")
public class UserController {

    private final CreateUserUseCaseImpl createUserUseCaseImpl;
    private final DeleteUserUseCaseImpl deleteUserUseCaseImpl;
    private final RetrieveUserUseCaseImpl retrieveUserUseCaseImpl;
    private final UpdateUserUseCaseImpl updateUserUseCaseImpl;
    private final UserMapper userMapper;

    public UserController(CreateUserUseCaseImpl createUserUseCaseImpl,
                          DeleteUserUseCaseImpl deleteUserUseCaseImpl,
                          RetrieveUserUseCaseImpl retrieveUserUseCaseImpl,
                          UpdateUserUseCaseImpl updateUserUseCaseImpl, DeleteUserUseCaseImpl deleteUserUseCaseImpl1, RetrieveUserUseCaseImpl retrieveUserUseCaseImpl1, UpdateUserUseCaseImpl updateUserUseCaseImpl1, UserMapper userMapper) {
        this.createUserUseCaseImpl = createUserUseCaseImpl;
        this.deleteUserUseCaseImpl = deleteUserUseCaseImpl1;
        this.retrieveUserUseCaseImpl = retrieveUserUseCaseImpl1;
        this.updateUserUseCaseImpl = updateUserUseCaseImpl1;
        this.userMapper = userMapper;
    }

    @PostMapping
    @Operation(summary = "Create a new user")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "CreateUserRequestDTO created successfully.",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid email address.",
                    content = @Content
            )
    })
    public Mono<ResponseEntity<UserResponseDTO>> createUser(@Valid @RequestBody CreateUserRequestDTO request) {
        User user = userMapper.toDomain(request);
        return createUserUseCaseImpl.createNewUser(user)
                .map(createdUser -> ResponseEntity
                                .created(URI.create("/users/" + createdUser.getUserName()))
                                .body(userMapper.toResponseDTO(createdUser))
                )
                .onErrorResume(error ->
                        Mono.just(ResponseEntity.badRequest().build())
                );
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get a user profile with all their collections")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "CreateUserRequestDTO found.",
                    content = @Content(schema = @Schema(implementation = UserWithCollectionsResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "CreateUserRequestDTO not found.",
                    content = @Content
            )
    })
    public Mono<ResponseEntity<UserWithCollectionsResponseDTO>> getUserById(@PathVariable UUID userId) {
        return retrieveUserUseCaseImpl.getUserById(userId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Get the list of all users")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Users retrieved successfully.",
                    content = @Content(
                            schema = @Schema(implementation = UserResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No users found.",
                    content = @Content
            )
    })
    public Mono<ResponseEntity<List<UserResponseDTO>>> getAllUsers() {
        return retrieveUserUseCaseImpl.getAllUsers()
                .collectList()
                .map(users ->
                        users.isEmpty()
                                ? ResponseEntity.notFound().build()
                                : ResponseEntity.ok(users)
                );
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Update the username of a specific user")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "CreateUserRequestDTO updated successfully.",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "CreateUserRequestDTO not found.",
                    content = @Content
            )
    })
    public Mono<ResponseEntity<UserResponseDTO>> updateUserName(@PathVariable UUID userId, @Valid @RequestBody UpdateUserNameRequestDTO dto) {
        return updateUserUseCaseImpl.updateNameByUserId(userId, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Update the email of a specific user")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "CreateUserRequestDTO updated successfully.",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "CreateUserRequestDTO not found.",
                    content = @Content
            )
    })
    public Mono<ResponseEntity<UserResponseDTO>> updateUserName(@PathVariable UUID userId, @Valid @RequestBody UpdateEmailRequestDTO dto) {
        return updateUserUseCaseImpl.updateEmailByUserId(userId, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Update the username of a specific user")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "CreateUserRequestDTO updated successfully.",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "CreateUserRequestDTO not found.",
                    content = @Content
            )
    })
    public Mono<ResponseEntity<UserResponseDTO>> updateUserName(@PathVariable UUID userId, @Valid @RequestBody UpdatePasswordRequestDTO dto) {
        return updateUserUseCaseImpl.updatePasswordByUserId(userId, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete a user by their ID")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "CreateUserRequestDTO deleted successfully."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "CreateUserRequestDTO not found."
            )
    })
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable UUID userId) {
        return deleteUserUseCaseImpl.deleteUser(userId)
                .map(deleted -> ResponseEntity.noContent().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}