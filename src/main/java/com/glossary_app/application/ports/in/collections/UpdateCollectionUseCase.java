package com.glossary_app.application.ports.in.collections;

import com.glossary_app.domain.model.Card;
import com.glossary_app.domain.model.Collection;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface UpdateCollectionUseCase {
    Mono<Collection> updateCollectionName(UUID collectionId, String newName);
    Mono<Collection> addCardToCollection(UUID collectionId, String frontText, String backText);
    Mono<Collection> renameCardFrontText(UUID collectionId, Long cardId, String newFrontText);
    Mono<Collection> renameCardBackText(UUID collectionId, Long cardId, String newBackText);
    Mono<Collection> deleteCardFromCollection(UUID collectionId, Long cardId);
}