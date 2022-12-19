package com.roop.admin.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


import com.roop.admin.user.UserRepository;
import com.roop.common.entity.user;

public class roopUsersDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepo;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		user user= userRepo.getUserByEmail(email);
		if(user!=null) {
			return new roopUserDetails(user);
		}
		throw new UsernameNotFoundException("Could not find user with email: "+ email);
		
	}

}
