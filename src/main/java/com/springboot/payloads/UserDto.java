package com.springboot.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	
	@NotEmpty
	@Size(min=4,message="Username should be minimum of 4 characters")
	private String name;

	@Email(message="Email entered is not valid")
	private String email;

	@NotEmpty()
	@Size(min=6,max=10,message="Password should be between 6 to 8 characters")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*#?&]{8,}$" )
	private String password;

	@NotEmpty
	private String about;
}
