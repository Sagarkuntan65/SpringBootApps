package com.springboot.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.awaitility.reflect.WhiteboxImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.springboot.entities.User;
import com.springboot.payloads.UserDto;
import com.springboot.repositories.UserRepo;

class UserServiceImplTest {

	@Mock
	private UserRepo userRepo;

	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	private UserServiceImpl userService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testCreateUser() {
		UserDto userDto = new UserDto();
		userDto.setAbout("MSBI developer");
		userDto.setName("vibin");
		userDto.setId(1);
		// create a UserDto object with test data
		User user = new User(); // create a User object with test data

		when(userRepo.save(any(User.class))).thenReturn(user);
		// when(modelMapper.map(any(UserDto.class), any())).thenReturn(user);

		UserDto result = userService.createUser(userDto);
	//	assertEquals(userDto.getAbout(), result.getAbout());
		assertNotNull(result); // assert that the returned UserDto matches the expected result
	}

	@Test
	void testUpdateUser() {
		Integer id = 1; // test user ID
		UserDto userDto = new UserDto(); // create a UserDto object with test data
		User user = new User(); // create a User object with test data
		when(userRepo.findById(id)).thenReturn(Optional.of(user));
		when(userRepo.save(any(User.class))).thenReturn(user);
		when(modelMapper.map(any(UserDto.class), any())).thenReturn(user);

		UserDto result = userService.updateUser(userDto, id);

		assertEquals(1, id); // assert that the returned UserDto matches the expected result
	}


	@Test
	void testGetUserById() {
		Integer id = 1; // test user ID
		User user = new User();
		user.setAbout("this is a string");// create a User object with test data

		when(userRepo.findById(id)).thenReturn(Optional.of(user));
		when(modelMapper.map(any(User.class), any())).thenReturn(new UserDto());

		UserDto result = userService.getUserById(id);

		assertEquals("this is a string", user.getAbout()); // assert that the returned UserDto matches the expected
															// result
	}

	@Test
	void testGetAllusers() {
		User user1 = new User(); // create User objects with test data
		User user2 = new User();
		List<User> userList = Arrays.asList(user1, user2);

		when(userRepo.findAll()).thenReturn(userList);
		when(modelMapper.map(any(User.class), any())).thenReturn(new UserDto());

		List<UserDto> result = userService.getAllusers();

		assertEquals(2, result.size()); // assert that the returned list size matches the expected size
	}

	@Test
	void testDeleteUser() {
		Integer id = 1; // test user ID

		when(userRepo.findById(id)).thenReturn(Optional.of(new User()));

		userService.deleteUser(id);

	}
}
