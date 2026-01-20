package com.glossary_app.infrastructure.mappers.collection;

import com.glossary_app.domain.model.Card;
import com.glossary_app.domain.model.Collection;
import com.glossary_app.infrastructure.entities.CardEntity;
import com.glossary_app.infrastructure.entities.CollectionEntity;
import org.mapstruct.Mapper;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CollectionPersistenceMapper {

    default Collection toDomain(CollectionEntity entity, Set<CardEntity> cardEntities) {
        if (entity == null) return null;

        Set<Card> cards = cardEntities == null
                ? Set.of()
                : cardEntities.stream()
                .map(this::toCardDomain)
                .collect(Collectors.toSet());

        return new Collection(
                entity.getCollectionId(),
                entity.getCollectionName(),
                cards
        );
    }

    CollectionEntity toEntity(Collection collection, UUID userId);

    default Card toCardDomain(CardEntity cardEntity) {
        if (cardEntity == null) return null;
        return new Card(cardEntity.getFrontText(), cardEntity.getBackText());
    }

    default CardEntity toCardEntity(Card card, UUID collectionId) {
        if (card == null) return null;
        CardEntity entity = new CardEntity();
        entity.setCollectionId(collectionId);
        entity.setFrontText(card.getFrontText());
        entity.setBackText(card.getBackText());
        return entity;
    }

    default Set<CardEntity> toCardEntitySet(Set<Card> cards, UUID collectionId) {
        if (cards == null) return Set.of();
        return cards.stream()
                .map(c -> toCardEntity(c, collectionId))
                .collect(Collectors.toSet());
    }
}