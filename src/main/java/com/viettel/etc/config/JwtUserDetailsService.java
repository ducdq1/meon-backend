package com.viettel.etc.config;

import java.util.ArrayList;

import com.viettel.etc.repositories.tables.UsersRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.UsersEntity;
import com.viettel.etc.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersRepositoryJPA usersRepositoryJPA;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsersEntity entity = usersRepositoryJPA.getByPhoneAndIsActive(username, Constants.IS_ACTIVE);
		if (entity !=null && entity.getPhone().equals(username)) {
			return new User(username, entity.getId().toString(),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}