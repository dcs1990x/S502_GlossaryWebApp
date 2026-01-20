package com.glossary_app.infrastructure.mappers.user;

import com.glossary_app.domain.dtos.query.UserWithCollections;
import com.glossary_app.domain.dtos.request.CreateUserRequestDTO;
import com.glossary_app.domain.dtos.response.UserResponseDTO;
import com.glossary_app.domain.dtos.response.UserWithCollectionsResponseDTO;
import com.glossary_app.domain.model.Collection;
import com.glossary_app.domain.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    default User toDomain(CreateUserRequestDTO createUserRequestDTO){
        return User.createNewUser(
                createUserRequestDTO.email(),
                createUserRequestDTO.password()
        );
    }
    UserResponseDTO toUserResponseDTO(User user);
    UserWithCollectionsResponseDTO toUserWithCollectionsResponseDTO(UserWithCollections userWithCollections);
}
