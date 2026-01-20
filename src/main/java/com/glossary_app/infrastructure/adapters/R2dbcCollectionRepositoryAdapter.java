package com.glossary_app.infrastructure.adapters;

import com.glossary_app.application.ports.out.CollectionRepositoryPort;
import com.glossary_app.domain.model.Card;
import com.glossary_app.domain.model.Collection;
import com.glossary_app.infrastructure.entities.CardEntity;
import com.glossary_app.infrastructure.entities.CollectionEntity;
import com.glossary_app.infrastructure.mappers.collection.CollectionPersistenceMapper;
import com.glossary_app.infrastructure.persistence.CardRepository;
import com.glossary_app.infrastructure.persistence.CollectionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Set;
import java.util.UUID;

@Component
public class R2dbcCollectionRepositoryAdapter implements CollectionRepositoryPort {

    private final CollectionRepository collectionRepository;
    private final CardRepository cardRepository;
    private final CollectionPersistenceMapper collectionPersistenceMapper;

    public R2dbcCollectionRepositoryAdapter(
            CollectionRepository collectionRepository,
            CardRepository cardRepository,
            CollectionPersistenceMapper collectionPersistenceMapper
    ) {
        this.collectionRepository = collectionRepository;
        this.cardRepository = cardRepository;
        this.collectionPersistenceMapper = collectionPersistenceMapper;
    }

    @Override
    public Mono<Collection> saveCollection(Collection collection) {
        CollectionEntity entity = collectionPersistenceMapper.toEntity(collection);
        Set<CardEntity> cardEntities = collectionPersistenceMapper.toCardEntitySet(collection.getCards(), collection.getCollectionId());
        return collectionRepository.save(entity)
                .map(savedEntity -> collectionPersistenceMapper.toDomain(savedEntity, cardEntities));
    }

    @Override
    public Mono<Collection> findCollectionById(UUID collectionId) {
        return collectionRepository.findById(collectionId)
                .switchIfEmpty(Mono.empty())
                .flatMap(collectionEntity ->
                        cardRepository.findAllCardsByCollectionId(collectionId)
                                .collectList()
                                .map(cardEntities -> {
                                    Set<CardEntity> cardSet = Set.copyOf(cardEntities);
                                    return collectionPersistenceMapper.toDomain(collectionEntity, cardSet);
                                })
                );
    }

    @Override
    public Flux<Collection> findCollectionsByUserId(UUID userId) {
        return collectionRepository.findAllCollectionsByUserId(userId)
                .flatMap(entity -> findCollectionById(entity.getCollectionId()));
    }

    @Override
    public Flux<Card> findCardsByCollectionId(UUID collectionId) {
        return cardRepository.findAllCardsByCollectionId(collectionId)
                .map(collectionPersistenceMapper::toCardDomain);
    }

    @Override
    public Mono<Void> deleteCollectionById(UUID collectionId) {
        return collectionRepository.deleteById(collectionId);
    }
}