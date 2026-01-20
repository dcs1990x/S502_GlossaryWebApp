package com.glossary_app.infrastructure.controller;

import com.glossary_app.application.ports.in.users.CreateUserUseCase;
import com.glossary_app.application.ports.in.users.DeleteUserUseCase;
import com.glossary_app.application.ports.in.users.RetrieveUserUseCase;
import com.glossary_app.application.ports.in.users.UpdateUserUseCase;
import com.glossary_app.domain.dtos.request.*;
import com.glossary_app.domain.dtos.response.UserResponseDTO;
import com.glossary_app.domain.dtos.response.UserWithCollectionsResponseDTO;
import com.glossary_app.domain.exceptions.ErrorResponse;
import com.glossary_app.domain.exceptions.InvalidEmailException;
import com.glossary_app.domain.exceptions.InvalidPasswordException;
import com.glossary_app.domain.exceptions.UserNotFoundException;
import com.glossary_app.domain.model.Collection;
import com.glossary_app.domain.model.User;
import com.glossary_app.infrastructure.mappers.collection.CollectionMapper;
import com.glossary_app.infrastructure.mappers.user.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "CRUD controller managing user actions")
public class UserController {

    private final CreateUserUseCase createUserUseCaseImpl;
    private final DeleteUserUseCase deleteUserUseCaseImpl;
    private final RetrieveUserUseCase retrieveUserUseCaseImpl;
    private final UpdateUserUseCase updateUserUseCaseImpl;
    private final UserMapper userMapper;
    private final CollectionMapper collectionMapper;

    public UserController(CreateUserUseCase createUserUseCaseImpl,
                          DeleteUserUseCase deleteUserUseCaseImpl,
                          RetrieveUserUseCase retrieveUserUseCaseImpl,
                          UpdateUserUseCase updateUserUseCaseImpl,
                          UserMapper userMapper, CollectionMapper collectionMapper) {
        this.createUserUseCaseImpl = createUserUseCaseImpl;
        this.deleteUserUseCaseImpl = deleteUserUseCaseImpl;
        this.retrieveUserUseCaseImpl = retrieveUserUseCaseImpl;
        this.updateUserUseCaseImpl = updateUserUseCaseImpl;
        this.userMapper = userMapper;
        this.collectionMapper = collectionMapper;
    }

    @PostMapping
    @Operation(summary = "Create a new user")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "User created successfully.",
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
                                .body(userMapper.toUserResponseDTO(createdUser))
                )
                .onErrorResume(error ->
                        Mono.just(ResponseEntity.badRequest().build())
                );
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get a user")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User found.",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found.",
                    content = @Content
            )
    })
    public Mono<ResponseEntity<UserResponseDTO>> getUserById(@PathVariable UUID userId) {
        return retrieveUserUseCaseImpl.getUserById(userId)
                .map(userMapper::toUserResponseDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/{userId}/collections")
    @Operation(summary = "Get a user profile with all their collections")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User found.",
                    content = @Content(schema = @Schema(implementation = UserWithCollectionsResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found.",
                    content = @Content
            )
    })
    public Mono<ResponseEntity<UserWithCollectionsResponseDTO>> getUserWithCollections(@PathVariable UUID userId) {
        return retrieveUserUseCaseImpl.getUserWithCollections(userId)
                .map(userMapper::toUserWithCollectionsResponseDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Get all users")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Users retrieved successfully.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserResponseDTO.class)))
            )
    })
    public Flux<ResponseEntity<UserResponseDTO>> getAllUsers() {
        return retrieveUserUseCaseImpl.getAllUsers()
                .map(userMapper::toUserResponseDTO)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Update the username of a specific user")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Username updated successfully.",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Username not found.",
                    content = @Content
            )
    })
    public Mono<ResponseEntity<UserResponseDTO>> updateUserName(@PathVariable UUID userId, @Valid @RequestBody UpdateUserNameRequestDTO dto) {
        return updateUserUseCaseImpl.updateNameByUserId(userId, dto.newUserName())
                .map(userMapper::toUserResponseDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{userId}/email")
    @Operation(summary = "Update the email of a specific user")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Email updated successfully.",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found.",
                    content = @Content
            )
    })
    public Mono<ResponseEntity<UserResponseDTO>> updateEmail(@PathVariable UUID userId, @Valid @RequestBody UpdateEmailRequestDTO dto) {
        return updateUserUseCaseImpl.updateEmailByUserId(userId, dto.newEmailAddress())
                .map(userMapper::toUserResponseDTO)
                .map(ResponseEntity::ok)
                .onErrorResume(InvalidEmailException.class,
                        e -> Mono.just(ResponseEntity.status(403).build()))
                .onErrorResume(UserNotFoundException.class,
                        e -> Mono.just(ResponseEntity.notFound().build()));
    }

    @PutMapping("/{userId}/password")
    @Operation(summary = "Update the password of a specific user")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Password updated successfully.",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found.",
                    content = @Content
            )
    })
    public Mono<ResponseEntity<UserResponseDTO>> updatePassword(@PathVariable UUID userId, @Valid @RequestBody UpdatePasswordRequestDTO dto) {
        return updateUserUseCaseImpl.updatePasswordByUserId(userId, dto.newPassword())
                .map(userMapper::toUserResponseDTO)
                .map(ResponseEntity::ok)
                .onErrorResume(InvalidPasswordException.class,
                        e -> Mono.just(ResponseEntity.status(403).build()))
                .onErrorResume(UserNotFoundException.class,
                        e -> Mono.just(ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete a user by their ID")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "User deleted successfully."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid User ID.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Not authenticated - invalid or expired TWT token",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    public Mono<ResponseEntity<Void>> deleteUser(
            @PathVariable
            @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
                     message = "User ID must be a valid UUID.")
            UUID userId) {
        return deleteUserUseCaseImpl.deleteUser(userId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}