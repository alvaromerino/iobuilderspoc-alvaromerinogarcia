package io.builders.poc.alvaromerinogarcia.usersapp.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.builders.poc.alvaromerinogarcia.usersapp.domain.data.UserDto;

public interface UsersController {
	
	@PostMapping
	ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto);
	
    @GetMapping("/{userId}")
    ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer userId);

    @GetMapping
    ResponseEntity<List<UserDto>> getUsers();
    
    @GetMapping("/findByName/{username}")
    ResponseEntity<UserDto> existsUser(@PathVariable("username") String username);

}
