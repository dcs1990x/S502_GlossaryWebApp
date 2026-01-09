package com.glossary_app.infrastructure.mappers;

import com.glossary_app.domain.model.User;
import com.glossary_app.infrastructure.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {

    default User toDomain(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        return User.fromEntityToModel(
                entity.getUserId(),
                entity.getUserName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getCreatedDate(),
                entity.getDeletedDate(),
                entity.getCollections()
                        .stream()
                        .map(CollectionMapper::toDomain)
                        .toList()
        );
    }

    @Mapping(target = "collections", ignore = true)
    UserEntity toEntity(User user);
}