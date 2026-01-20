package com.glossary_app.infrastructure.mappers.collection;

import com.glossary_app.domain.dtos.request.CreateCollectionRequestDTO;
import com.glossary_app.domain.dtos.response.CardResponseDTO;
import com.glossary_app.domain.dtos.response.CollectionResponseDTO;
import com.glossary_app.domain.model.Card;
import com.glossary_app.domain.model.Collection;
import org.mapstruct.Mapper;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CollectionMapper {

    default Collection toDomain(CreateCollectionRequestDTO dto) {
        return new Collection(
                java.util.UUID.randomUUID(),
                dto.collectionName(),
                Set.of()
        );
    }
    default CollectionResponseDTO toCollectionResponseDTO(Collection collection) {
        Set<CardResponseDTO> cardSet = collection.getCards().stream()
                .map(this::toCardResponseDTO)
                .collect(Collectors.toSet());

        return new CollectionResponseDTO(
                collection.getCollectionId(),
                collection.getCollectionName(),
                cardSet
        );
    }

    default CardResponseDTO toCardResponseDTO(Card card) {
        return new CardResponseDTO(
                card.getCardId(),
                card.getFrontText(),
                card.getBackText()
        );
    }
}