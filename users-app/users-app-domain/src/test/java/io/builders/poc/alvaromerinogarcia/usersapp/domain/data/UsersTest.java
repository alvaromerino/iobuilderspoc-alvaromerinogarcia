package io.builders.poc.alvaromerinogarcia.usersapp.domain.data;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UsersTest 
{
    @Test
    public void givenAttributes_whenUserFullConstructor_thenAttributeAreSet() {
    	final UserDto userDto = new UserDto(1, "user1", "pass1");
    	assertThat(userDto.getId()).isEqualTo(1);
    	assertThat(userDto.getUsername()).isEqualTo("user1");
    	assertThat(userDto.getPassword()).isEqualTo("pass1");
    }

    @Test
    public void givenTwoUsers_whenSameAttributes_thenEqual() {
    	final UserDto userDto1 = new UserDto(1, "user1", "pass1");
    	final UserDto userDto2 = new UserDto(1, "user1", "pass1");
        assertThat(userDto1).isEqualTo(userDto2);
    }

    @Test
    public void givenTwoUsers_whenDifferentName_thenNotEqual() {
    	final UserDto userDto1 = new UserDto(1, "user1", "pass1");
    	final UserDto userDto2 = new UserDto(1, "user2", "pass1");
        assertThat(userDto1).isNotEqualTo(userDto2);
    }

    @Test
    public void givenTwoUsers_whenSameAttributes_thenSameHashCode() {
    	final UserDto userDto1 = new UserDto(1, "user1", "pass1");
    	final UserDto userDto2 = new UserDto(1, "user1", "pass1");
        assertThat(userDto1.hashCode()).isEqualTo(userDto2.hashCode());
    }

    @Test
    public void givenTwoUsers_whenDifferentName_thenNotSameHashCode() {
    	final UserDto userDto1 = new UserDto(1, "user1", "pass1");
    	final UserDto userDto2 = new UserDto(1, "user2", "pass1");
        assertThat(userDto1.hashCode()).isNotEqualTo(userDto2.hashCode());
    }
    
    @Test
    public void givenUser_whenToString_thenSeeADescriptiveString() {
    	
    	Integer id = 1;
    	String username = "user1";
    	String password = "pass1";
    	
        final UserDto userDto = new UserDto(id, username, password);
        String expected = "UserDto [id=" + id + ", username=" + username + ", password=" + password + "]";
        assertThat(userDto.toString()).isEqualTo(expected);
    }

}
