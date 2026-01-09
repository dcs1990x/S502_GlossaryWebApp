package com.glossary_app.application.ports.out.collection;

import com.glossary_app.infrastructure.entities.CollectionEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import java.util.UUID;

public interface CollectionRepositoryPort extends ReactiveCrudRepository<CollectionEntity, UUID> {
    Flux<CollectionEntity> findByUserId(UUID userId);
}