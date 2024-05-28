package com.springboot.services;

import java.util.List;

import com.springboot.payloads.UserDto;

public interface UserService {

	UserDto createUser(UserDto user);

	UserDto updateUser(UserDto user, Integer id);

	UserDto getUserById(Integer id);

	List<UserDto> getAllusers();

	void deleteUser(Integer id);

}
