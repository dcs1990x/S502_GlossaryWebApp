package com.glossary_app.repository;

import com.glossary_app.domain.entities.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Repository
public interface UserRepository extends ReactiveCrudRepository<UserEntity, UUID> {
    Flux<UserEntity> findAllByPlayerId(UUID userId);
    Mono<UserEntity> findByGameId(UUID userId);
    Mono<Void> deleteByUserId(UUID userId);
}
