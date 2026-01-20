package com.glossary_app.domain.model;

import java.util.Objects;

public class Card {

    private final Long cardId;
    private String frontText;
    private String backText;

    public Card(Long cardId, String frontText, String backText) {
        this.cardId = cardId;
        if (frontText == null || frontText.isBlank()) {
            throw new IllegalArgumentException("Front text cannot be empty.");
        }
        if (backText == null || backText.isBlank()) {
            throw new IllegalArgumentException("Back text cannot be empty.");
        }
        this.frontText = frontText;
        this.backText = backText;
    }

    public Long getCardId() {
        return cardId;
    }

    public String getFrontText() {
        return frontText;
    }

    public String getBackText() {
        return backText;
    }

    public void setFrontText(String newFrontText) {
        if (newFrontText == null || newFrontText.isBlank()) {
            throw new IllegalArgumentException("New front text cannot be empty.");
        }
        this.frontText = newFrontText;
    }

    public void setBackText(String newBackText) {
        if (newBackText == null || newBackText.isBlank()) {
            throw new IllegalArgumentException("New front text cannot be empty.");
        }
        this.backText = newBackText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card card)) return false;
        return frontText.equals(card.frontText)
                && backText.equals(card.backText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frontText, backText);
    }
}