package io.builders.poc.alvaromerinogarcia.usersapp.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.builders.poc.alvaromerinogarcia.usersapp.domain.data.UserDto;
import io.builders.poc.alvaromerinogarcia.usersapp.domain.exception.UserNotFoundException;
import io.builders.poc.alvaromerinogarcia.usersapp.domain.port.UserPersistencePort;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
    private UserPersistencePort usersPersistencePort;

    public UsersServiceImpl() {
		super();
	}

	public UsersServiceImpl(UserPersistencePort usersPersistencePort) {
        this.usersPersistencePort = usersPersistencePort;
    }

    @Override
    public UserDto addUser(UserDto userDto) {
    	return usersPersistencePort.addUser(userDto);
    }

    @Override
    @Transactional
    public void removeUser(UserDto UserDto) {
    	usersPersistencePort.removeUser(UserDto);
    }

    @Override
    public void updateUser(UserDto UserDto) {
    	usersPersistencePort.updateUser(UserDto);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return usersPersistencePort.getAllUsers();
    }

    @Override
    public UserDto getUserByID(Integer userId) {
        return usersPersistencePort.getUserByID(userId);
    }

}
