package com.glossary_app.domain.model;

import java.util.UUID;

public class Card {
    private final UUID cardId;
    private final String frontText;
    private final String backText;

    public Card(UUID cardId, String frontText, String backText) {
        this.cardId = cardId;
        this.frontText = frontText;
        this.backText = backText;
    }

    public  UUID getCardId() {
        return cardId;
    }

    public String getFrontText() {
        return frontText;
    }

    public String getBackText() {
        return backText;
    }
}