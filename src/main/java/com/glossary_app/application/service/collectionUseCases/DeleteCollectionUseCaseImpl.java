package com.glossary_app.application.service.collectionUseCases;

import com.glossary_app.application.ports.in.collections.DeleteCollectionUseCase;
import com.glossary_app.application.ports.out.CollectionRepositoryPort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service
public class DeleteCollectionUseCaseImpl implements DeleteCollectionUseCase {

    private final CollectionRepositoryPort collectionRepositoryPort;

    public DeleteCollectionUseCaseImpl(CollectionRepositoryPort collectionRepositoryPort) {
        this.collectionRepositoryPort = collectionRepositoryPort;
    }

    @Override
    public Mono<Void> deleteCollection(UUID collectionId) {
        return collectionRepositoryPort.deleteCollectionById(collectionId);
    }
}