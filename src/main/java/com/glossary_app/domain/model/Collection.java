package com.glossary_app.domain.model;

import java.util.Set;
import java.util.UUID;

public class Collection {
    private final UUID collectionId;
    private String collectionName;
    private final Set<Card> cards;

    public Collection(UUID collectionId, String collectionName, Set<Card> cards) {
        if (collectionName == null || collectionName.isBlank()) {
            throw new IllegalArgumentException("Collection name cannot be empty.");
        }
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

    public Set<Card> getCards() {
        return Set.copyOf(cards);
    }

    public void rename(String newName) {
        if (newName.isBlank()) {
            throw new IllegalArgumentException("Collection name cannot be empty.");
        }
        this.collectionName = newName;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        if (cards.isEmpty() || !cards.remove(card)) {
            throw new IllegalStateException("Collection is empty.");
        }
        cards.remove(card);
    }
}