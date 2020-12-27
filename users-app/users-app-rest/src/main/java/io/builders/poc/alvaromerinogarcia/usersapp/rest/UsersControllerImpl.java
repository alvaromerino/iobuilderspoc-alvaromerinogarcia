package io.builders.poc.alvaromerinogarcia.usersapp.rest;

import java.util.Optional;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import io.builders.poc.alvaromerinogarcia.usersapp.core.service.UsersService;
import io.builders.poc.alvaromerinogarcia.usersapp.domain.data.UserDto;
import io.builders.poc.alvaromerinogarcia.usersapp.domain.exception.UserNotFoundException;

@RestController("/")
public class UsersControllerImpl implements UsersController {

	private final UsersService usersService;

    public UsersControllerImpl(UsersService usersService) {
        this.usersService = usersService;
    }

	@Override
	public ResponseEntity<UserDto> createUser(UserDto userDto) {
		UserDto u = usersService.addUser(userDto);
		if (u == null) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		return new ResponseEntity<>(u, HttpStatus.CREATED);
	}
    
	@Override
	public ResponseEntity<UserDto> getUserById(Integer userId) {
		try {
			return new ResponseEntity<>(usersService.getUserByID(userId), HttpStatus.OK);
		} catch (UserNotFoundException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<List<UserDto>> getUsers() {
        return new ResponseEntity<>(usersService.getAllUsers(), HttpStatus.OK);
	}
	
	@Override
    public ResponseEntity<UserDto> existsUser(String username) {
		List<UserDto> users = usersService.getAllUsers();
		Optional<UserDto> optUserDto = users
			.stream()
			.filter(item -> item.getUsername().equals(username))
			.findFirst();
		
		if (optUserDto.isPresent()) {
			return new ResponseEntity<>(optUserDto.get(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		
	}

}
