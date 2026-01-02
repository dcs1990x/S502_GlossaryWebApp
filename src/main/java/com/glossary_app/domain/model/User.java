package com.glossary_app.domain.model;

import lombok.Builder;
import lombok.Getter;
import java.time.Instant;
import java.util.UUID;

@Getter
@Builder(toBuilder = true)
public class User {
    private final UUID userId;
    private final String userName;
    private final String email;
    private final String password;
    private final Instant createdDate;
    private final boolean isActive;
    private final Instant deletedDate;

    public static User createNewUser(String email, String password) {
        validateEmail(email);
        return User.builder()
                .userId(UUID.randomUUID())
                .userName(extractUserNameFromEmail(email))
                .email(email.trim())
                .password(password)
                .createdDate(Instant.now())
                .isActive(true)
                .deletedDate(null)
                .build();
    }

    public static User fromEntityToModel(
            UUID userId,
            String userName,
            String email,
            String password,
            Instant createdDate,
            boolean isActive,
            Instant deletedDate
    ) {
        validateEmail(email);
        return User.builder()
                .userId(userId)
                .userName(userName)
                .email(email)
                .password(password)
                .createdDate(createdDate)
                .isActive(isActive)
                .deletedDate(deletedDate)
                .build();
    }

    private static void validateEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
    }

    private static String extractUserNameFromEmail(String email) {
        return email.substring(0, email.indexOf("@"));
    }

    public User deactivate() {
        return this.toBuilder()
                .isActive(false)
                .deletedDate(Instant.now())
                .build();
    }

    public User activate() {
        return this.toBuilder()
                .isActive(true)
                .deletedDate(null)
                .build();
    }

    public boolean isDeleted() {
        return deletedDate != null;
    }
}