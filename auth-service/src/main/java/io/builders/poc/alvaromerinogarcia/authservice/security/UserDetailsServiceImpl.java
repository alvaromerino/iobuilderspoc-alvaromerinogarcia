package io.builders.poc.alvaromerinogarcia.authservice.security;

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
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserDetailsServiceImpl implements UserDetailsService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private RestTemplate restTemplate;
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		LOGGER.info("Loading by username...");

		String role = "ROLE_USER";
		Map userMap = null;

		LOGGER.info("Getting user info from users microservice...");
		userMap = getUserInfoFromUsersService(username);

		if (userMap != null && userMap.values().size() > 0) {
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                    .commaSeparatedStringToAuthorityList(role);

            // The "User" class is provided by Spring and represents a model class for user to be returned by UserDetailsService
			// And used by auth manager to verify and check user authentication.
			LOGGER.info("Returning user with username " + username);
            return new User((String) userMap.get("username"), encoder.encode((String) userMap.get("password")), grantedAuthorities);
		}

		LOGGER.info("User with user " + username + " not found");
        // If user not found. Throw this exception.
        throw new UsernameNotFoundException("Username: " + username + " not found");
	}
	
	@HystrixCommand(fallbackMethod = "fallback")
	public Map getUserInfoFromUsersService(String username) {

    	Map<String, String> vars = new HashMap<>();
    	vars.put("username", username);

    	Map<String, Object> userMap = new HashMap<String, Object>();
    	userMap = restTemplate.getForObject("http://users-service/findByName/{username}", Map.class, vars);

		return userMap;
	}

	public Map fallback(Throwable hystrixCommand) {
		return null;
	}

}