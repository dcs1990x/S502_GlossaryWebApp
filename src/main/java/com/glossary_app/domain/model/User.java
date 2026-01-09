package com.glossary_app.domain.model;

import lombok.Builder;
import lombok.Getter;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Builder(toBuilder = true)
public class User {
    private final UUID userId;
    private final String userName;
    private final String email;
    private final String password;
    private final Instant createdDate;
    private final Instant deletedDate;
    private final List<Collection> collections;

    public static User createNewUser(String email, String password) {
        validateEmail(email);
        return User.builder()
                .userId(UUID.randomUUID())
                .userName(extractUserNameFromEmail(email))
                .email(email.trim())
                .password(password)
                .createdDate(Instant.now())
                .deletedDate(null)
                .collections(new ArrayList<>())
                .build();
    }

    public static User fromEntityToModel(
            UUID userId,
            String userName,
            String email,
            String password,
            Instant createdDate,
            Instant deletedDate,
            List<Collection> collections
    ) {
        validateEmail(email);
        return User.builder()
                .userId(userId)
                .userName(userName)
                .email(email)
                .password(password)
                .createdDate(createdDate)
                .deletedDate(deletedDate)
                .collections(collections)
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
                .deletedDate(Instant.now())
                .build();
    }

    public User activate() {
        return this.toBuilder()
                .deletedDate(null)
                .build();
    }

    public boolean isDeleted() {
        return deletedDate != null;
    }
}