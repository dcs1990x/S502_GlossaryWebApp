package com.glossary_app.application.ports.in.collections;

import reactor.core.publisher.Mono;
import java.util.UUID;

public interface DeleteCollectionUseCase {
    Mono<Void> deleteCollection(UUID CollectionId);
}