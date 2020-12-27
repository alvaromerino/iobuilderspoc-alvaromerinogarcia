package io.builders.poc.alvaromerinogarcia.usersapp.domain.port;

import java.util.List;

import io.builders.poc.alvaromerinogarcia.usersapp.domain.data.UserDto;
import io.builders.poc.alvaromerinogarcia.usersapp.domain.exception.UserNotFoundException;

public interface UserPersistencePort {

    UserDto addUser(UserDto userDto);

    void removeUser(UserDto userDto);

    void updateUser(UserDto userDto);

    List<UserDto> getAllUsers();

    UserDto getUserByID(Integer userID);
}
