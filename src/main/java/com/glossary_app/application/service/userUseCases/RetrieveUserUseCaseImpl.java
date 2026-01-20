package com.glossary_app.application.service.userUseCases;

import com.glossary_app.application.ports.in.users.RetrieveUserUseCase;
import com.glossary_app.application.ports.out.CollectionRepositoryPort;
import com.glossary_app.application.ports.out.UserRepositoryPort;
import com.glossary_app.domain.dtos.query.UserWithCollections;
import com.glossary_app.domain.model.Collection;
import com.glossary_app.domain.model.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.UUID;

@Service
public class RetrieveUserUseCaseImpl implements RetrieveUserUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final CollectionRepositoryPort collectionRepositoryPort;

    public RetrieveUserUseCaseImpl(UserRepositoryPort userRepositoryPort, CollectionRepositoryPort collectionRepositoryPort){
        this.userRepositoryPort = userRepositoryPort;
        this.collectionRepositoryPort = collectionRepositoryPort;
    }

    @Override
    public Mono<User> getUserById(UUID userId) {
        return userRepositoryPort.findUserById(userId);
    }

    @Override
    public Mono<UserWithCollections> getUserWithCollections(UUID userId) {
        Mono<User> userMono = userRepositoryPort.findUserById(userId);
        Mono<List<Collection>> collectionsMono =
                collectionRepositoryPort.findCollectionsByUserId(userId)
                        .flatMap(collection ->
                                collectionRepositoryPort
                                        .findCardsByCollectionId(collection.getCollectionId())
                                        .collectList()
                                        .map(cards -> {
                                            cards.forEach(collection::addCard);
                                            return collection;
                                        })
                        )
                        .collectList();
        return userMono.zipWith(
                collectionsMono,
                UserWithCollections::new
        );
    }

    @Override
    public Flux<User> getAllUsers() {
        return userRepositoryPort.findAllUsers();
    }
}