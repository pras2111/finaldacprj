package com.shareBuddy.service;

import java.util.Set;

import com.shareBuddy.pojos.User; 
import com.shareBuddy.pojos.security.PasswordResetToken;
import com.shareBuddy.pojos.security.UserRole;

public interface UserService {
	
	PasswordResetToken getPasswordResetToken(final String token);
	
	void createPasswordResetTokenForUser(final User user, final String token);
	
	User findByUsername(String username);
	
	User findByEmail(String email);

	User createUser(User user, Set<UserRole> userRoles) throws Exception;

	User save(User user);
	 
 

	User findByPhone(String userPhone);
}
