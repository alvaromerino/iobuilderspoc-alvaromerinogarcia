package io.builders.poc.alvaromerinogarcia.usersapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.builders.poc.alvaromerinogarcia.usersapp.domain.port.UserPersistencePort;
import io.builders.poc.alvaromerinogarcia.usersapp.memorydatabase.adapter.UsersInMemoryDatabase;

@Configuration
public class AppConfiguration {

    @Bean
    public UserPersistencePort userPersistencePort() {
        return new UsersInMemoryDatabase();
    }

}