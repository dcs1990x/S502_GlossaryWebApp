package com.glossary_app.application.service.collectionUseCases;

import com.glossary_app.application.ports.in.collections.UpdateCollectionUseCase;
import com.glossary_app.application.ports.out.CollectionRepositoryPort;
import com.glossary_app.domain.model.Card;
import com.glossary_app.domain.model.Collection;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service
public class UpdateCollectionUseCaseImpl implements UpdateCollectionUseCase {

    private final CollectionRepositoryPort collectionRepositoryPort;

    public UpdateCollectionUseCaseImpl(CollectionRepositoryPort collectionRepositoryPort) {
        this.collectionRepositoryPort = collectionRepositoryPort;
    }

    @Override
    public Mono<Collection> addCardToCollection(UUID collectionId, String card) {
        return null;
    }

    @Override
    public Mono<Collection> updateCollectionName(UUID collectionId, String name) {
        return null;
    }

    @Override
    public Mono<Collection> deleteCardFromCollection(UUID collectionId, Card cardId) {
        return null;
    }
}