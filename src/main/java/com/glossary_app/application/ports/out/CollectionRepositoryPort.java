package com.glossary_app.application.ports.out;

import com.glossary_app.domain.model.Card;
import com.glossary_app.domain.model.Collection;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface CollectionRepositoryPort {
    Mono<Collection> saveCollection(Collection collection);
    Mono<Collection> findCollectionById(UUID collectionId);
    Flux<Collection> findCollectionsByUserId(UUID userId);
    Flux<Card> findCardsByCollectionId(UUID collectionId);
    Mono<Void> deleteCollectionById(UUID collectionId);
}