package com.glossary_app.domain.model;

import java.util.Objects;

public final class Card {

    private final String frontText;
    private final String backText;

    public Card(String frontText, String backText) {
        if (frontText == null || frontText.isBlank()) {
            throw new IllegalArgumentException("Front text cannot be empty.");
        }
        if (backText == null || backText.isBlank()) {
            throw new IllegalArgumentException("Back text cannot be empty.");
        }
        this.frontText = frontText;
        this.backText = backText;
    }

    public String getFrontText() {
        return frontText;
    }

    public String getBackText() {
        return backText;
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