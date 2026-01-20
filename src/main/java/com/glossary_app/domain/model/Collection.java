package com.glossary_app.domain.model;

import java.util.Objects;
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
        renameCollection(collectionName);
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

    public void renameCollection(String newCollectionName) {
        if (newCollectionName.isBlank()) {
            throw new IllegalArgumentException("Collection name cannot be empty.");
        }
        this.collectionName = newCollectionName;
    }

    public Card findCard(Long cardId) {
        Objects.requireNonNull(cardId, "Card ID cannot be null");
        return cards.stream()
                .filter(card -> cardId.equals(card.getCardId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Card not found."));
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void updateCardFrontText(Long cardId, String newText) {
        Card card = findCard(cardId);
        card.setFrontText(newText);
    }

    public void updateCardBackText(Long cardId, String newText) {
        Card card = findCard(cardId);
        card.setBackText(newText);
    }

    public void removeCard(Long cardId) {
        Objects.requireNonNull(cardId, "Card ID cannot be null.");
        boolean removed = cards.removeIf(card -> cardId.equals(card.getCardId()));

        if (!removed) {
            throw new IllegalArgumentException(
                    String.format("Card with ID %d not found in collection", cardId)
            );
        }
    }
}