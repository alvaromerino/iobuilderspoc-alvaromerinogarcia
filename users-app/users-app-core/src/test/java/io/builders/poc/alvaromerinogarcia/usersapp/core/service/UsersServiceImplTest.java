package io.builders.poc.alvaromerinogarcia.usersapp.core.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.builders.poc.alvaromerinogarcia.usersapp.domain.data.UserDto;
import io.builders.poc.alvaromerinogarcia.usersapp.domain.port.UserPersistencePort;


@ExtendWith(MockitoExtension.class)
public class UsersServiceImplTest {

    @InjectMocks
    private UsersServiceImpl usersServicePort;

    @Mock
    private UserPersistencePort usersPersistencePort;

    @Mock
    private List<UserDto> mockUsersDtoList;

    @Test
    public void givenUsers_whenAdd_thenAddPortCalled() {
        final UserDto mockUserDto = mock(UserDto.class);
        usersServicePort.addUser(mockUserDto);
        verify(usersPersistencePort, only()).addUser(mockUserDto);
    }

    @Test
    public void givenUser_whenRemove_thenRemovePortCalled() {
    	final UserDto mockUserDto = mock(UserDto.class);
        usersServicePort.removeUser(mockUserDto);
        verify(usersPersistencePort, only()).removeUser(mockUserDto);
    }


    @Test
    public void givenUser_whenUpdate_thenUpdatePortCalled() {
    	final UserDto mockUserDto = mock(UserDto.class);
    	usersServicePort.updateUser(mockUserDto);
        verify(usersPersistencePort, only()).updateUser(mockUserDto);
    }

    @Test
    public void givenCallToAllUsers_whenNothingSpecified_thenGetAllUsersPortCalled() {
        when(usersPersistencePort.getAllUsers()).thenReturn(mockUsersDtoList);
        final List<UserDto> allUserDtos = usersServicePort.getAllUsers();
        assertThat(allUserDtos).isSameAs(mockUsersDtoList);
        verify(usersPersistencePort, only()).getAllUsers();
    }

    @Test
    public void givenUsersId_whenGetUsersById_thenGetUsersByIdPortCalled() {
        final Integer testUserId = 1;
        final UserDto mockUserDto = mock(UserDto.class);
        when(usersPersistencePort.getUserByID(testUserId)).thenReturn(mockUserDto);

        final UserDto userDto = usersServicePort.getUserByID(testUserId);

        assertThat(userDto).isSameAs(mockUserDto);
        verify(usersPersistencePort, only()).getUserByID(testUserId);
    }
    
}