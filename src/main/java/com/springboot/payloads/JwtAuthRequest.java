package com.springboot.payloads;

import lombok.Data;

@Data
public class JwtAuthRequest {
	
	private String Username;
	
	private String Password;

}
