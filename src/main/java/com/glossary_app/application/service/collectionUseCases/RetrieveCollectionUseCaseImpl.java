package com.glossary_app.application.service.collectionUseCases;

import com.glossary_app.application.ports.in.collections.RetrieveCollectionUseCase;
import com.glossary_app.application.ports.out.CollectionRepositoryPort;
import com.glossary_app.domain.model.Collection;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service
public class RetrieveCollectionUseCaseImpl implements RetrieveCollectionUseCase {

    private final CollectionRepositoryPort collectionRepositoryPort;

    public RetrieveCollectionUseCaseImpl(CollectionRepositoryPort collectionRepositoryPort) {
        this.collectionRepositoryPort = collectionRepositoryPort;
    }

    @Override
    public Mono<Collection> getCollectionById(UUID collectionId) {
        return collectionRepositoryPort.findCollectionById(collectionId)
                .switchIfEmpty(Mono.empty())
                .flatMap(this::addCardsToCollection);
    }

    @Override
    public Flux<Collection> getCollectionsByUserId(UUID userId) {
        return collectionRepositoryPort.findCollectionsByUserId(userId)
                .flatMap(this::addCardsToCollection);
    }

    private Mono<Collection> addCardsToCollection(Collection collection) {
        return collectionRepositoryPort.findCardsByCollectionId(collection.getCollectionId())
                .collectList()
                .map(cards -> {
                    cards.forEach(collection::addCard);
                    return collection;
                });
    }
}