package com.glossary_app.application.ports.in.users;

import com.glossary_app.domain.dtos.request.UpdateEmailRequestDTO;
import com.glossary_app.domain.dtos.request.UpdatePasswordRequestDTO;
import com.glossary_app.domain.dtos.request.UpdateUserNameRequestDTO;
import com.glossary_app.domain.model.User;
import java.util.UUID;

public interface UpdateUserUseCase {
    User updateNameByUserId(UUID userId, UpdateUserNameRequestDTO updateUserNameRequestDTO);
    User updateEmailByUserId(UUID userId, UpdateEmailRequestDTO updateEmailRequestDTO);
    User updatePasswordByUserId(UUID userId, UpdatePasswordRequestDTO updatePasswordRequestDTO);
}