package com.glossary_app.application.service.usecases;

import com.glossary_app.application.ports.in.users.RetrieveUserUseCase;
import com.glossary_app.application.ports.out.UserRepositoryPort;
import com.glossary_app.domain.dtos.response.CardResponseDTO;
import com.glossary_app.domain.dtos.response.CollectionResponseDTO;
import com.glossary_app.domain.dtos.response.UserWithCollectionsResponseDTO;
import com.glossary_app.domain.model.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service
public class RetrieveUserUseCaseImpl implements RetrieveUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public RetrieveUserUseCaseImpl(UserRepositoryPort userRepositoryPort){
        this.userRepositoryPort = userRepositoryPort;
    }

    public Mono<UserWithCollectionsResponseDTO> getUserById(UUID userId) {
        Mono<User> userMono = userRepositoryPort.findUserById(userId);
        Flux<CollectionResponseDTO> collectionsFlux =
                collectionRepository.findByUserId(userId)
                        .flatMap(collection ->
                                cardRepository.findAllCardsByCollectionId(collection.getCollectionId())
                                        .map(card -> new CardResponseDTO(
                                                card.getFrontText(),
                                                card.getBackText(),
                                                card.getCreatedDate()
                                        ))
                                        .collectList()
                                        .map(cardList -> new CollectionResponseDTO(
                                                collection.getCollectionName(),
                                                cardList
                                        ))
                        );
        return userMono.zipWith(
                collectionsFlux.collectList(),
                (user, collections) -> new UserWithCollectionsResponseDTO(
                        user.getUserName(),
                        user.getEmail(),
                        collections
                )
        );
    }

    @Override
    public Flux<User> getAllUsers() {
        return userRepositoryPort.findAllUsers();
    }
}