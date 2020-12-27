package io.builders.poc.alvaromerinogarcia.authservice.security;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserDetailsServiceImpl implements UserDetailsService  {

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private RestTemplate restTemplate;
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    	String role = "ROLE_USER";
    	
    	Map<String, String> vars = new HashMap<>();
    	vars.put("username", username);

    	Map<String, Object> userMap = new HashMap<String, Object>();
    	userMap = restTemplate.getForObject("http://users-service/findByName/{username}", Map.class, vars);

    	if (userMap != null && userMap.values().size() > 0) {
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                    .commaSeparatedStringToAuthorityList(role);

            // The "User" class is provided by Spring and represents a model class for user to be returned by UserDetailsService
            // And used by auth manager to verify and check user authentication.
            return new User((String) userMap.get("username"), encoder.encode((String) userMap.get("password")), grantedAuthorities);
		}

        // If user not found. Throw this exception.
        throw new UsernameNotFoundException("Username: " + username + " not found");
    }

}