package com.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.Exceptions.ResourceNotFoundException;
import com.springboot.entities.User;
import com.springboot.repositories.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Load username from database
		
		User user=userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException(username, "email->"+username, 0));
		return user;
	}

}
