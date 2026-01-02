package com.glossary_app.infrastructure.entities;

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
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
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
    private boolean isActive;
    private Instant deletedDate;
}
