package com.glossary_app.infrastructure.controller;

import com.glossary_app.application.ports.in.collections.CreateCollectionUseCase;
import com.glossary_app.application.ports.in.collections.DeleteCollectionUseCase;
import com.glossary_app.application.ports.in.collections.RetrieveCollectionUseCase;
import com.glossary_app.application.ports.in.collections.UpdateCollectionUseCase;
import com.glossary_app.infrastructure.mappers.collection.CollectionMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /*GET    /collections/{collectionId}
    PUT    /collections/{collectionId}
    POST   /collections/{collectionId}/cards
    DELETE /collections/{collectionId}/cards*/


}