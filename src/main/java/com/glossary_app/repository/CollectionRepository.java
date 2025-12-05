package com.glossary_app.repository;

import com.glossary_app.domain.entities.CollectionEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import java.util.UUID;

public interface CollectionRepository extends ReactiveCrudRepository<CollectionEntity, UUID> {
    Flux<CollectionEntity> findByUserId(UUID userId);
}