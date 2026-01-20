package com.glossary_app.application.service.collectionUseCases;

import com.glossary_app.application.ports.in.collections.UpdateCollectionUseCase;
import com.glossary_app.application.ports.out.CollectionRepositoryPort;
import com.glossary_app.domain.model.Card;
import com.glossary_app.domain.model.Collection;
import org.springframework.data.crossstore.ChangeSetPersister;
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
    public Mono<Collection> updateCollectionName(UUID collectionId, String name) {
        return collectionRepositoryPort.findCollectionById(collectionId)
                .switchIfEmpty(Mono.error(new ChangeSetPersister.NotFoundException()))
                .map(collection -> {
                    collection.renameCollection(name);
                    return collection;
                })
                .flatMap(collectionRepositoryPort::saveCollection);
    }

    @Override
    public Mono<Collection> addCardToCollection(
            UUID collectionId,
            String frontText,
            String backText
    ) {
        return collectionRepositoryPort.findCollectionById(collectionId)
                .switchIfEmpty(Mono.error(new ChangeSetPersister.NotFoundException()))
                .map(collection -> {
                    collection.addCard(
                            new Card(null, frontText, backText)
                    );
                    return collection;
                })
                .flatMap(collectionRepositoryPort::saveCollection);
    }

    @Override
    public Mono<Collection> renameCardFrontText(
            UUID collectionId,
            Long cardId,
            String newFrontText
    ) {
        return collectionRepositoryPort.findCollectionById(collectionId)
                .switchIfEmpty(Mono.error(new ChangeSetPersister.NotFoundException()))
                .map(collection -> {
                    collection.updateCardFrontText(cardId, newFrontText);
                    return collection;
                })
                .flatMap(collectionRepositoryPort::saveCollection);
    }

    @Override
    public Mono<Collection> renameCardBackText(
            UUID collectionId,
            Long cardId,
            String newBackText
    ) {
        return collectionRepositoryPort.findCollectionById(collectionId)
                .switchIfEmpty(Mono.error(new ChangeSetPersister.NotFoundException()))
                .map(collection -> {
                    collection.updateCardBackText(cardId, newBackText);
                    return collection;
                })
                .flatMap(collectionRepositoryPort::saveCollection);
    }

    @Override
    public Mono<Collection> deleteCardFromCollection(UUID collectionId, Long cardId) {
        return collectionRepositoryPort.findCollectionById(collectionId)
                .switchIfEmpty(Mono.error(new ChangeSetPersister.NotFoundException()))
                .map(collection -> {
                    collection.removeCard(cardId);
                    return collection;
                })
                .flatMap(collectionRepositoryPort::saveCollection);
    }
}