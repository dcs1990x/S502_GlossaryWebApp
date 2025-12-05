package com.glossary_app.controller;

import com.glossary_app.domain.dtos.request.CreateUserRequestDTO;
import com.glossary_app.domain.dtos.request.UpdateUserNameRequestDTO;
import com.glossary_app.domain.dtos.response.UserResponseDTO;
import com.glossary_app.domain.dtos.response.UserWithCollectionsResponseDTO;
import com.glossary_app.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@Tag(name = "Glossary API", description = "REST API for Glossary Study")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/new")
    @Operation(summary = "Create a new user")
    @ApiResponse(responseCode = "201 CREATED", description = "The user was changed successfully.")
    public Mono<ResponseEntity<UserResponseDTO>> postNewUser(@RequestBody CreateUserRequestDTO createUserRequestDTO){
        return userService.createNewUser(createUserRequestDTO)
                .map(userResponseDTO -> ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO));
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get a user profile by their ID")
    @ApiResponse(responseCode = "200 OK", description = "The user was retrieved successfully.")
    @ApiResponse(responseCode = "404 NOT FOUND", description = "The user with the entered ID could not be found.")
    public Mono<ResponseEntity<UserWithCollectionsResponseDTO>> getUser(@PathVariable UUID userId) {
        return userService.getUserProfile(userId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Get all users list")
    @ApiResponse(responseCode = "200 OK", description = "The users list was retrieved successfully.")
    public Flux<ResponseEntity<UserWithCollectionsResponseDTO>> getAllUsersList() {
        return userService.findAll()
                .map(userReponseDTO -> ResponseEntity.status(HttpStatus.OK).body(userReponseDTO));
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Change user name")
    @ApiResponse(responseCode = "200 OK", description = "The user name was changed successfully.")
    @ApiResponse(responseCode = "404 NOT FOUND", description = "The user with the entered ID could not be found.")
    public Mono<ResponseEntity<UserResponseDTO>> putUserName(@PathVariable UUID userId, @RequestBody UpdateUserNameRequestDTO updateUserNameRequestDTO){
        return userService.updateUserName(userId, updateUserNameRequestDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete a user by their ID")
    @ApiResponse(responseCode = "204", description = "The user was deleted successfully.")
    public Mono<ResponseEntity<Void>> deletePlayer(@PathVariable UUID userId) {
        return userService.deleteUserById(userId)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}