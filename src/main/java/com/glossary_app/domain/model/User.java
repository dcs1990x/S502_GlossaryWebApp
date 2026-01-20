package com.glossary_app.domain.model;

import lombok.Builder;
import java.time.Instant;
import java.util.UUID;

@Builder(toBuilder = true)
public class User {

    private final UUID userId;
    private final String userName;
    private final String email;
    private final String password;
    private final Instant createdDate;
    private final Instant deletedDate;

    public UUID getUserId() { return userId; }
    public String getUserName() { return userName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Instant getCreatedDate() { return createdDate; }
    public Instant getDeletedDate() { return deletedDate; }

    public User withName(String newName) {
        return new User(this.userId, newName, this.email, this.password, this.createdDate, this.deletedDate);
    }

    public User withEmail(String newEmail) {
        return new User(this.userId, this.userName, newEmail, this.password, this.createdDate, this.deletedDate);
    }

    public User withPassword(String newPassword) {
        return new User(this.userId, this.userName, this.email, newPassword, this.createdDate, this.deletedDate);
    }

    public static User createNewUser(String email, String password) {
        validateEmail(email);
        validatePassword(password);
        return User.builder()
                .userId(UUID.randomUUID())
                .userName(extractUserNameFromEmail(email))
                .email(email.trim())
                .password(password)
                .createdDate(Instant.now())
                .deletedDate(null)
                .build();
    }

    public static User fromEntityToModel(
            UUID userId,
            String userName,
            String email,
            String password,
            Instant createdDate,
            Instant deletedDate
    ) {
        validateEmail(email);
        validatePassword(password);
        return User.builder()
                .userId(userId)
                .userName(userName)
                .email(email)
                .password(password)
                .createdDate(createdDate)
                .deletedDate(deletedDate)
                .build();
    }

    private static void validateUserName(String userName) {
        if (userName == null || userName.isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
    }

    private static void validateEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
    }

    private static void validatePassword(String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be empty.");
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