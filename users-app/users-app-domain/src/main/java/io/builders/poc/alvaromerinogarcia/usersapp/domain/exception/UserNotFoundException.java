package io.builders.poc.alvaromerinogarcia.usersapp.domain.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Integer id) {
        super(String.format("User with id %d not found!", id));
    }
}
