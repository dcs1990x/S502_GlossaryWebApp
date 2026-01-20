package com.glossary_app.infrastructure.persistence;

import com.glossary_app.infrastructure.entities.CardEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import java.util.UUID;

@Repository
public interface CardRepository extends ReactiveCrudRepository<CardEntity, UUID> {
    Flux<CardEntity> findAllCardsByCollectionId(UUID collectionId);
}