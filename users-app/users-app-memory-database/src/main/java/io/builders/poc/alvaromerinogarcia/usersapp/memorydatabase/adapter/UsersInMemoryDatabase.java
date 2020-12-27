package io.builders.poc.alvaromerinogarcia.usersapp.memorydatabase.adapter;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import io.builders.poc.alvaromerinogarcia.usersapp.domain.data.UserDto;
import io.builders.poc.alvaromerinogarcia.usersapp.domain.exception.UserNotFoundException;
import io.builders.poc.alvaromerinogarcia.usersapp.domain.port.UserPersistencePort;
import io.builders.poc.alvaromerinogarcia.usersapp.memorydatabase.model.UserEntity;


public class UsersInMemoryDatabase implements UserPersistencePort {

	private Set<UserEntity> users = new HashSet<UserEntity>();

	@Override
	public UserDto addUser(UserDto userDto) {
		
		UserDto uDto = null;
		try {
			uDto = getUserByName(userDto.getUsername());
			return null;
		} catch(UserNotFoundException unfe) {
			Integer id = users.size() + 1;
			uDto = new UserDto(id, userDto.getUsername(), userDto.getPassword());
			users.add(getUserEntity(uDto));
			return uDto;
		}
		
	}

	@Override
	public void removeUser(UserDto userDto) {
		users.remove(getUserEntity(userDto));
	}

	@Override
	public void updateUser(UserDto userDto) {
		Optional<UserEntity> optUser = users.stream().filter(item -> item.getId().equals(userDto.getId())).findFirst();
		if (optUser.isPresent()) {
			UserEntity ue = optUser.get();
			users.remove(ue);
			users.add(getUserEntity(userDto));
		}
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<UserDto> userDtos = users.stream().map(item -> getUserDto(item)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public UserDto getUserByID(Integer userID) throws UserNotFoundException {
		Optional<UserEntity> optUser = users
				.stream()
				.filter(item -> item.getId().equals(userID))
				.findFirst();
		
		if (!optUser.isPresent()) {
			throw new UserNotFoundException(userID);
		}
		else {
			return getUserDto(optUser.get());
		}
	}
	
	public UserDto getUserByName(String username) throws UserNotFoundException {
		Optional<UserEntity> optUser = users
				.stream()
				.filter(item -> item.getUsername().equals(username))
				.findFirst();
		
		if (!optUser.isPresent()) {
			throw new UserNotFoundException(-1);
		}
		else {
			return getUserDto(optUser.get());
		}
	}
	
	private UserEntity getUserEntity(UserDto userDto) {
		if (userDto == null) {
			return null;
		}
        
		return new UserEntity(userDto.getId(), userDto.getUsername(), userDto.getPassword());
    }
	
	private UserDto getUserDto(UserEntity userEntity) {
		if (userEntity == null) {
			return null;
		}
		
		return new UserDto(userEntity.getId(), userEntity.getUsername(), userEntity.getPassword());
    }


	
}
