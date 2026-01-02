package com.glossary_app.infrastructure.mappers;

import com.glossary_app.domain.model.User;
import com.glossary_app.infrastructure.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class ModelEntityMapper {

    public User toDomain(UserEntity entity) {
        return User.fromEntityToModel(
                entity.getUserId(),
                entity.getUserName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getCreatedDate(),
                entity.isActive(),
                entity.getDeletedDate()
        );
    }

    public UserEntity toEntity(User user) {
        return UserEntity.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .password(user.getPassword())
                .createdDate(user.getCreatedDate())
                .isActive(user.isActive())
                .deletedDate(user.getDeletedDate())
                .build();
    }
}