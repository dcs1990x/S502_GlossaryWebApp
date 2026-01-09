package com.glossary_app.domain.model;

import java.util.List;
import java.util.UUID;

public class Collection {
    private final UUID collectionId;
    private final String collectionName;
    private final List<Card> cards;

    public Collection(UUID collectionId, String collectionName, List<Card> cards) {
        this.collectionId = collectionId;
        this.collectionName = collectionName;
        this.cards = cards;
    }

    public UUID getCollectionId() {
        return collectionId;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}