package com.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.payloads.JwtAuthRequest;
import com.springboot.security.JwtAuthResponse;
import com.springboot.security.JwtTokenHelper;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	/*
	 * Here JWtAuthRequest is taken from the request body
	 * Authenticate() method is called
	 * 
	 * 
	 * 
	 * */

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest jwtAuthRequest) {
		String username = jwtAuthRequest.getUsername();
		String Password = jwtAuthRequest.getPassword();
		this.authenticate(username, Password);
		UserDetails User = this.userDetailsService.loadUserByUsername(username);
		String generateToken = this.jwtTokenHelper.generateToken(User);
		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
		jwtAuthResponse.setToken(generateToken);
		return new ResponseEntity<JwtAuthResponse>(jwtAuthResponse, HttpStatus.OK);
	}

	/*
	 *UsernamePasswordAuthenticationToken method accepts principal and credentials but does not authenticate
	 * 
	 * 
	 * 
	 * */
	
	private void authenticate(String UserName, String Password) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(UserName, Password);
try {
	this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

}catch (DisabledException e) {
	}

}
}
