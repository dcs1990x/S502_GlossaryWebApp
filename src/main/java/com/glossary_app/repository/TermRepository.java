package com.glossary_app.repository;

import com.glossary_app.domain.entities.TermEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Repository
public interface TermRepository extends ReactiveCrudRepository<TermEntity, UUID> {
    Flux<TermEntity> findAllByUserId(UUID playerId);
    Mono<TermEntity> findByTermId(Long gameId);
    Mono<Void> deleteByTermId(Long gameId);
}