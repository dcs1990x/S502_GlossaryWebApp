package com.glossary_app.domain.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Table("users")
public class UserEntity {

    @Id
    private UUID userId;

    @NotBlank
    private String userName;

    @NotBlank(message = "Please enter your email.")
    @Email(message = "Invalid email format.")
    private String email;

    @NotBlank
    @Size(min = 4, message = "Password size should be at least 4 characters long.")
    private String password;

    private Instant createdDate;
    private Instant deletedDate;

    public UserEntity(String email, String password) {
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        this.userId = UUID.randomUUID();
        this.userName = email.substring(0, email.indexOf("@"));
        this.email = email;
        this.password = password;
        this.createdDate = Instant.now();
    }
}
