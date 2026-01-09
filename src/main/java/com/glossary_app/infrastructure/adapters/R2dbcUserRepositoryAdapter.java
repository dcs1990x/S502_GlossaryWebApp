package com.glossary_app.infrastructure.adapters;

import com.glossary_app.application.ports.out.user.DeleteUserRepositoryPort;
import com.glossary_app.application.ports.out.user.FindUserRepositoryPort;
import com.glossary_app.application.ports.out.user.SaveUserRepositoryPort;
import com.glossary_app.application.ports.out.user.UpdateUserRepositoryPort;
import com.glossary_app.infrastructure.mappers.UserPersistenceMapper;
import com.glossary_app.infrastructure.entities.UserEntity;
import com.glossary_app.domain.model.User;
import com.glossary_app.infrastructure.persistence.UserRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Repository
public class R2dbcUserRepositoryAdapter implements SaveUserRepositoryPort, FindUserRepositoryPort, UpdateUserRepositoryPort, DeleteUserRepositoryPort {

    private final UserRepository userRepository;
    private final UserPersistenceMapper userPersistenceMapper;

    public R2dbcUserRepositoryAdapter(UserRepository userRepository, UserPersistenceMapper userPersistenceMapper) {
        this.userRepository = userRepository;
        this.userPersistenceMapper = userPersistenceMapper;
    }

    @Override
    public Mono<User> saveUser(User user) {
        UserEntity entity = userPersistenceMapper.toEntity(user);
        return userRepository.save(entity)
                .map(userPersistenceMapper::toDomain);
    }

    @Override
    public Mono<User> findUserById(UUID userId) {
        return userRepository.findById(userId)
                .map(userPersistenceMapper::toDomain);
    }

    @Override
    public Mono<User> findByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .map(userPersistenceMapper::toDomain);
    }

    @Override
    public Flux<User> findAllUsers() {
        return userRepository.findAll()
                .map(userPersistenceMapper::toDomain);
    }

    /*@Override
    public Mono<Boolean> existsByEmail(String email){
        return userRepository.findUserByEmail(email)
                .map(UserEntity::isActive);
    }*/

    @Override
    public Mono<Void> deleteUserById(UUID userId) {
        userRepository.deleteById(userId);
        return null;
    }
}
