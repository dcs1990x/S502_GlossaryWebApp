package com.glossary_app.application.ports.in.collections;

import com.glossary_app.domain.model.Collection;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface CreateCollectionUseCase {
    Mono<Collection> createNewCollection(Collection collection, UUID userId);
}