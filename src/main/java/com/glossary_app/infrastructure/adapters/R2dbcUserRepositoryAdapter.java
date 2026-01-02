package com.glossary_app.infrastructure.adapters;

import com.glossary_app.application.ports.out.UserRepositoryPort;
import com.glossary_app.infrastructure.mappers.ModelEntityMapper;
import com.glossary_app.infrastructure.entities.UserEntity;
import com.glossary_app.domain.model.User;
import com.glossary_app.infrastructure.persistence.UserRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Repository
public class R2dbcUserRepositoryAdapter implements UserRepositoryPort {

    private final UserRepository userRepository;
    private final ModelEntityMapper modelEntityMapper;

    public R2dbcUserRepositoryAdapter(UserRepository userRepository, ModelEntityMapper modelEntityMapper) {
        this.userRepository = userRepository;
        this.modelEntityMapper = modelEntityMapper;
    }

    @Override
    public Mono<User> saveUser(User user) {
        UserEntity entity = modelEntityMapper.toEntity(user);
        return userRepository.save(entity)
                .map(modelEntityMapper::toDomain);
    }

    @Override
    public Mono<User> findUserById(UUID userId) {
        return userRepository.findById(userId)
                .map(modelEntityMapper::toDomain);
    }

    @Override
    public Mono<User> findByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .map(modelEntityMapper::toDomain);
    }

    @Override
    public Flux<User> findAllUsers() {
        return userRepository.findAll()
                .map(modelEntityMapper::toDomain);
    }

    @Override
    public Mono<Boolean> existsByEmail(String email){
        return userRepository.findUserByEmail(email)
                .map(UserEntity::isActive);
    }

    @Override
    public Mono<Void> deleteUserById(UUID userId) {
        userRepository.deleteById(userId);
        return null;
    }
}
