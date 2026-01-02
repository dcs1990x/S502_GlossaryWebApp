package com.glossary_app.infrastructure.persistence;

import com.glossary_app.infrastructure.entities.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface UserRepository extends ReactiveCrudRepository<UserEntity, UUID> {
    Mono<UserEntity> findUserByEmail(String email);
}
