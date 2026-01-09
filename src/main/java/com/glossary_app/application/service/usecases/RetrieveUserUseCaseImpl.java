package com.glossary_app.application.service.usecases;

import com.glossary_app.application.ports.in.users.RetrieveUserUseCase;
import com.glossary_app.application.ports.out.user.FindUserRepositoryPort;
import com.glossary_app.application.ports.out.user.SaveUserRepositoryPort;
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

    private final FindUserRepositoryPort findUserRepositoryPort;

    public RetrieveUserUseCaseImpl(SaveUserRepositoryPort saveUserRepositoryPort, FindUserRepositoryPort findUserRepositoryPort){
        this.findUserRepositoryPort = findUserRepositoryPort;
    }

    public Mono<UserWithCollectionsResponseDTO> getUserById(UUID userId) {
        Mono<User> userMono = findUserRepositoryPort.findUserById(userId);
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
        return findUserRepositoryPort.findAllUsers();
    }
}