package com.glossary_app.infrastructure.mappers;

import com.glossary_app.domain.dtos.request.CreateCardRequestDTO;
import com.glossary_app.domain.dtos.request.CreateUserRequestDTO;
import com.glossary_app.domain.model.User;
import org.mapstruct.Mapper;

import javax.smartcardio.Card;

@Mapper(componentModel = "spring")
public interface DTOEntityMapper {

    User toDomain(CreateUserRequestDTO createUserRequestDTO);
    Card toDomain(CreateCardRequestDTO createCardRequestDTO);
}
