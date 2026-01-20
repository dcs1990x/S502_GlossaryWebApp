package com.glossary_app.infrastructure.controller;

import com.glossary_app.application.ports.in.collections.CreateCollectionUseCase;
import com.glossary_app.application.ports.in.collections.DeleteCollectionUseCase;
import com.glossary_app.application.ports.in.collections.RetrieveCollectionUseCase;
import com.glossary_app.application.ports.in.collections.UpdateCollectionUseCase;
import com.glossary_app.domain.dtos.request.*;
import com.glossary_app.domain.dtos.response.CollectionResponseDTO;
import com.glossary_app.domain.dtos.response.UserResponseDTO;
import com.glossary_app.domain.exceptions.ErrorResponse;
import com.glossary_app.infrastructure.mappers.collection.CollectionMapper;
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
import java.util.UUID;

@RestController
@RequestMapping("/collections")
@Tag(name = "Collections", description = "CRUD controller managing collection actions")
public class CollectionController {

    private final CreateCollectionUseCase createCollectionUseCaseImpl;
    private final DeleteCollectionUseCase deleteCollectionUseCaseImpl;
    private final RetrieveCollectionUseCase retrieveCollectionUseCaseImpl;
    private final UpdateCollectionUseCase updateCollectionUseCaseImpl;
    private final CollectionMapper collectionMapper;

    public CollectionController(CreateCollectionUseCase createCollectionUseCaseImpl, DeleteCollectionUseCase deleteCollectionUseCaseImpl, RetrieveCollectionUseCase retrieveCollectionUseCaseImpl, UpdateCollectionUseCase updateCollectionUseCaseImpl, CollectionMapper collectionMapper) {
        this.createCollectionUseCaseImpl = createCollectionUseCaseImpl;
        this.deleteCollectionUseCaseImpl = deleteCollectionUseCaseImpl;
        this.retrieveCollectionUseCaseImpl = retrieveCollectionUseCaseImpl;
        this.updateCollectionUseCaseImpl = updateCollectionUseCaseImpl;
        this.collectionMapper = collectionMapper;
    }

    @PostMapping
    @Operation(summary = "Create a new collection")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Collection created successfully.",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid collection name.",
                    content = @Content
            )
    })
    public Mono<ResponseEntity<CollectionResponseDTO>> createCollection(@Valid @RequestBody CreateCollectionRequestDTO request) {
        return createCollectionUseCaseImpl.createNewCollection(request.userId(), request.collectionName())
                .map(createdCollection -> ResponseEntity
                        .created(URI.create("/collections/" + createdCollection.getCollectionName()))
                        .body(collectionMapper.toCollectionResponseDTO(createdCollection))
                )
                .onErrorResume(error ->
                        Mono.just(ResponseEntity.badRequest().build())
                );
    }

    @GetMapping("/{collectionId}")
    @Operation(summary = "Get a collection")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Collection found.",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Collection not found.",
                    content = @Content
            )
    })
    public Mono<ResponseEntity<CollectionResponseDTO>> getCollectionById(@PathVariable UUID collectionId) {
        return retrieveCollectionUseCaseImpl.getCollectionById(collectionId)
                .map(collectionMapper::toCollectionResponseDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{collectionId}/name")
    @Operation(summary = "Update the collection name of a specific user")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Collection name updated successfully.",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Collection not found.",
                    content = @Content
            )
    })
    public Mono<ResponseEntity<CollectionResponseDTO>> updateCollectionName(@PathVariable UUID collectionId, @Valid @RequestBody UpdateCollectionNameRequestDTO request) {
        return updateCollectionUseCaseImpl.updateCollectionName(collectionId, request.newName())
                .map(collectionMapper::toCollectionResponseDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/{collectionId}/cards")
    @Operation(summary = "Update the collection by adding a new card.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Card created successfully.",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Card could not be created.",
                    content = @Content
            )
    })
    public Mono<ResponseEntity<CollectionResponseDTO>> addCardToCollection(@PathVariable UUID collectionId, @Valid @RequestBody CreateCardRequestDTO request) {
        return updateCollectionUseCaseImpl.addCardToCollection(collectionId, request.frontText(), request.backText())
                .map(collectionMapper::toCollectionResponseDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{collectionId}/cards/{cardId}/front-text")
    @Operation(summary = "Update the front text of a card in a collection")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Card front text updated successfully.",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Card not found.",
                    content = @Content
            )
    })
    public Mono<ResponseEntity<CollectionResponseDTO>> updateCardFrontText(@PathVariable UUID userId, @PathVariable Long cardId, @Valid @RequestBody UpdateCollectionNameRequestDTO request) {
        return updateCollectionUseCaseImpl.renameCardFrontText(userId, cardId, request.newName())
                .map(collectionMapper::toCollectionResponseDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{collectionId}/cards/{cardId}/back-text")
    @Operation(summary = "Update the back text of a card in a collection")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Card back text updated successfully.",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Card not found.",
                    content = @Content
            )
    })
    public Mono<ResponseEntity<CollectionResponseDTO>> updateCardBackText(@PathVariable UUID userId, @PathVariable Long cardId, @Valid @RequestBody UpdateCollectionNameRequestDTO request) {
        return updateCollectionUseCaseImpl.renameCardBackText(userId, cardId, request.newName())
                .map(collectionMapper::toCollectionResponseDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{collectionId}/cards/{cardId}")
    @Operation(summary = "Update the collection by removing a card.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Card removed successfully.",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Card could not be found.",
                    content = @Content
            )
    })
    public Mono<ResponseEntity<CollectionResponseDTO>> deleteCardFromCollection(@PathVariable UUID collectionId, @PathVariable Long cardId) {
        return updateCollectionUseCaseImpl.deleteCardFromCollection(collectionId, cardId)
                .map(collectionMapper::toCollectionResponseDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{collectionId}")
    @Operation(summary = "Delete a collection by their ID")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Collection deleted successfully."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid Collection ID.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Not authenticated - invalid or expired JWT token.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Collection not found.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    public Mono<ResponseEntity<Void>> deleteCollection(@PathVariable UUID collectionId) {
        return deleteCollectionUseCaseImpl.deleteCollection(collectionId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}