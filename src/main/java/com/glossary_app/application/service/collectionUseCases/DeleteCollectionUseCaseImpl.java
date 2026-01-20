package com.glossary_app.application.service.collectionUseCases;

import com.glossary_app.application.ports.in.collections.DeleteCollectionUseCase;
import com.glossary_app.infrastructure.persistence.CollectionRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service
public class DeleteCollectionUseCaseImpl implements DeleteCollectionUseCase {

    private final CollectionRepository collectionRepository;

    public DeleteCollectionUseCaseImpl(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    @Override
    public Mono<Void> deleteCollection(UUID CollectionId) {
        return null;
    }
}