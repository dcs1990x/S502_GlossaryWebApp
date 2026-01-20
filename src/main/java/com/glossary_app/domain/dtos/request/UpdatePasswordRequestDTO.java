package com.glossary_app.domain.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdatePasswordRequestDTO(

        @NotBlank(message = "Current password is mandatory.")
        String currentPassword,

        @NotBlank(message = "Please enter a new password.")
        @Size(min = 3, max = 12, message = "The new password must be between 3 and 12 characters long.")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
                message = "The new password must contain at least a lower case, an upper case and a number."
        )
        String newPassword,

        @NotBlank(message = "Please confirm the new password.")
        String confirmPassword
) {

    public UpdatePasswordRequestDTO {
        if (newPassword != null && confirmPassword != null &&
                !newPassword.equals(confirmPassword)) {
            throw new IllegalArgumentException(
                    "The new and the confirmed passwords are not equal."
            );
        }

        if (newPassword != null && currentPassword != null &&
                newPassword.equals(currentPassword)) {
            throw new IllegalArgumentException(
                    "The new password cannot be the same as the current one."
            );
        }
    }
}