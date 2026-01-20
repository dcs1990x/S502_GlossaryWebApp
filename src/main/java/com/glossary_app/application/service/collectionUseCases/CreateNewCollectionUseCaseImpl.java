package com.glossary_app.application.service.collectionUseCases;

import com.glossary_app.application.ports.in.collections.CreateCollectionUseCase;
import com.glossary_app.application.ports.out.CollectionRepositoryPort;
import com.glossary_app.domain.model.Collection;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service
public class CreateNewCollectionUseCaseImpl implements CreateCollectionUseCase {

    private final CollectionRepositoryPort collectionRepositoryPort;

    public CreateNewCollectionUseCaseImpl(CollectionRepositoryPort collectionRepositoryPort) {
        this.collectionRepositoryPort = collectionRepositoryPort;
    }

    @Override
    public Mono<Collection> createNewCollection(Collection collection, UUID userId) {
        return null;
    }
}