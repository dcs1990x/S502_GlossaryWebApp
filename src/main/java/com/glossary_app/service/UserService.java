package com.glossary_app.service;

import com.glossary_app.domain.dtos.request.CreateUserRequestDTO;
import com.glossary_app.domain.dtos.request.UpdateUserNameRequestDTO;
import com.glossary_app.domain.dtos.response.CardResponseDTO;
import com.glossary_app.domain.dtos.response.CollectionResponseDTO;
import com.glossary_app.domain.dtos.response.UserResponseDTO;
import com.glossary_app.domain.dtos.response.UserWithCollectionsResponseDTO;
import com.glossary_app.domain.entities.UserEntity;
import com.glossary_app.repository.CardRepository;
import com.glossary_app.repository.CollectionRepository;
import com.glossary_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CollectionRepository collectionRepository;
    private final CardRepository cardRepository;

    public Mono<UserResponseDTO> createNewUser(CreateUserRequestDTO createUserRequestDTO){

    }

    public Mono<UserWithCollectionsResponseDTO> getUserProfile(UUID userId) {
        Mono<UserEntity> userMono = userRepository.findUserById(userId);
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

    public Mono<UserResponseDTO> updateUserName(UUID userId, UpdateUserNameRequestDTO updateUserNameRequestDTO){

    }

    public Mono<Void> deleteUserById(UUID userId){

    }
}