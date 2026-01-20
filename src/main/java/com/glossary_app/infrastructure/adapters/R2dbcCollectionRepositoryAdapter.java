package com.glossary_app.infrastructure.adapters;

import com.glossary_app.application.ports.out.CollectionRepositoryPort;
import com.glossary_app.domain.model.Card;
import com.glossary_app.domain.model.Collection;
import com.glossary_app.infrastructure.mappers.collection.CollectionPersistenceMapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

public class R2dbcCollectionRepositoryAdapter implements CollectionRepositoryPort {

    private final CollectionRepositoryPort collectionRepositoryPort;
    private final CollectionPersistenceMapper collectionPersistenceMapper;

    public R2dbcCollectionRepositoryAdapter(CollectionRepositoryPort collectionRepositoryPort, CollectionPersistenceMapper collectionPersistenceMapper) {
        this.collectionRepositoryPort = collectionRepositoryPort;
        this.collectionPersistenceMapper = collectionPersistenceMapper;
    }

    @Override
    public Mono<Collection> saveCollection(Collection collection) {
        return null;
    }

    @Override
    public Mono<Collection> findCollectionById(UUID collectionId) {
        return null;
    }

    @Override
    public Flux<Collection> findCollectionsByUserId(UUID userId) {
        return null;
    }

    @Override
    public Flux<Card> findCardsByCollectionId(UUID collectionId) {
        return null;
    }

    @Override
    public Mono<Collection> updateCollectionName(Collection collection) {
        return null;
    }

    @Override
    public Mono<Void> deleteCollectionById(UUID collectionId) {
        return null;
    }
}