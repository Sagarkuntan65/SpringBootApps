package com.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.payloads.ApiResponse;
import com.springboot.payloads.UserDto;
import com.springboot.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	/*
	 * @RequestBody annotation in Spring MVC indicates that the method parameter
	 * should be bound to the body of the HTTP request example below getUser, even
	 * though you're not directly using the userDto parameter in the method body,
	 * the @RequestBody annotation tells Spring to deserialize the JSON (or XML)
	 * payload of the incoming HTTP request body into a UserDto object and bind it
	 * to the userDto parameter.
	 * 
	 * ResponseEntity<List<UserDto>>: This indicates that the method returns a
	 * ResponseEntity containing a list of UserDto objects. The ResponseEntity class
	 * allows you to customize the HTTP response, including status codes, headers,
	 * and the response body.
	 */

	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getUser(@RequestBody UserDto userDto) { // why do we use request body when we
																					// are not using the parameter
		List<UserDto> createdUserDto = this.userService.getAllusers();
		return ResponseEntity.ok(createdUserDto); 
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer userId) {
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}

	@PostMapping("/addUser")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto dto = this.userService.createUser(userDto);
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}

	@PutMapping("/addUser/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,
			@PathVariable("userId") Integer userId) {
		UserDto dto2 = this.userService.updateUser(userDto, userId);
		return ResponseEntity.ok(dto2);
	}

	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId) {
		this.userService.deleteUser(userId);
		return new ResponseEntity(new ApiResponse("user deleted successfully", true, userId), HttpStatus.OK);
	}
	

	
	



	
}
