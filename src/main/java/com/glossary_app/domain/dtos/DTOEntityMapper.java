package com.glossary_app.domain.dtos;

import com.glossary_app.domain.dtos.request.CreateTermRequestDTO;
import com.glossary_app.domain.dtos.request.CreateUserRequestDTO;
import com.glossary_app.domain.entities.CardEntity;
import com.glossary_app.domain.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DTOEntityMapper {

    UserEntity toUserEntity(CreateUserRequestDTO createUserRequestDTO);
    CardEntity toEntity(CreateTermRequestDTO createTermRequestDTO);
}
