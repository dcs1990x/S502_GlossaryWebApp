package com.glossary_app.application.ports.in.collections;

import com.glossary_app.domain.model.Card;
import com.glossary_app.domain.model.Collection;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface UpdateCollectionUseCase {
    Mono<Collection> addCardToCollection(UUID collectionId, String card);
    Mono<Collection> updateCollectionName(UUID collectionId, String name);
    Mono<Collection> deleteCardFromCollection(UUID collectionId, Card cardId);
}