package com.glossary_app.infrastructure.persistence;

import com.glossary_app.infrastructure.entities.CollectionEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Repository
public interface CollectionRepository extends ReactiveCrudRepository<CollectionEntity, UUID> {
    Flux<CollectionEntity> findAllCollectionsByUserId(UUID userId);
    Mono<CollectionEntity> findById(UUID collectionId);
}