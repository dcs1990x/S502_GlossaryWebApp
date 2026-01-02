package com.glossary_app.infrastructure.config;

import com.glossary_app.application.ports.out.UserRepositoryPort;
import com.glossary_app.application.service.UserService;
import com.glossary_app.application.service.usecases.CreateUserUseCaseImpl;
import com.glossary_app.application.service.usecases.DeleteUserUseCaseImpl;
import com.glossary_app.application.service.usecases.RetrieveUserUseCaseImpl;
import com.glossary_app.application.service.usecases.UpdateUserUseCaseImpl;
import com.glossary_app.infrastructure.adapters.R2dbcUserRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public UserService userServiceConfig(UserRepositoryPort userRepositoryPort){
        return new UserService(
                new CreateUserUseCaseImpl(userRepositoryPort),
                new RetrieveUserUseCaseImpl(userRepositoryPort),
                new UpdateUserUseCaseImpl(userRepositoryPort),
                new DeleteUserUseCaseImpl(userRepositoryPort)
        );
    }

    @Bean
    public UserRepositoryPort userRepositoryPortConfig(R2dbcUserRepositoryAdapter r2dbcUserRepositoryAdapter){
        return r2dbcUserRepositoryAdapter;
    }

    /*
    @Bean
    public CollectionService collectionServiceConfig(UserRepositoryPort userRepositoryPort){
        return new UserService(
                new CreateUserUseCaseImpl(userRepositoryPort),
                new RetrieveUserUseCaseImpl(userRepositoryPort),
                new UpdateUserUseCaseImpl(userRepositoryPort),
                new DeleteUserUseCaseImpl(userRepositoryPort)
        );
    }

    @Bean
    public CollectionRepositoryPort collectionRepositoryPortConfig(R2dbcUserRepositoryAdapter r2dbcUserRepositoryAdapter){
        return r2dbcUserRepositoryAdapter;
    }

    @Bean
    public CardService cardServiceConfig(UserRepositoryPort userRepositoryPort){
        return new UserService(
                new CreateUserUseCaseImpl(userRepositoryPort),
                new RetrieveUserUseCaseImpl(userRepositoryPort),
                new UpdateUserUseCaseImpl(userRepositoryPort),
                new DeleteUserUseCaseImpl(userRepositoryPort)
        );
    }

    @Bean
    public CardRepositoryPort cardRepositoryPortConfig(R2dbcUserRepositoryAdapter r2dbcUserRepositoryAdapter){
        return r2dbcUserRepositoryAdapter;
    }
     */
}