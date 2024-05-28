package com.springboot.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.Exceptions.ResourceNotFoundException;
import com.springboot.entities.User;
import com.springboot.payloads.UserDto;
import com.springboot.repositories.UserRepo;
import com.springboot.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User savedSUer = userRepo.save(this.DtoToUser(userDto));
		UserDto userDto2 = this.userToDto(savedSUer); 
		return userDto2;
	}
	
//	@Override
//	public UserDto updateUser(UserDto userdto, Integer id) {
//		User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
//		user.setName(userdto.getName());
//		user.setAbout(userdto.getAbout());
//		user.setEmail(userdto.getEmail());
//		user.setPassword(userdto.getPassword());
//		
//
//	
//		User upadtedUser = this.userRepo.save(user);
//		return this.userToDto(upadtedUser); 
//		}
	
	public UserDto updateUser(UserDto userDto,Integer id ) {
		
		User user=this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User does not exist", "for this id + ", id));
		User updatedUser=this.userRepo.save(user);
		return this.userToDto(updatedUser);
		
	}
//
//	@Override
//	public UserDto getUserById(Integer id) {
//		User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
//		return this.userToDto(user);
//	}
//	
//	@Override
//	public List<UserDto> getAllusers() {
//		List<User> usersList = this.userRepo.findAll();
//		List<UserDto> dtolist=usersList.stream().map((users)->this.userToDto(users)).collect(Collectors.toList());
//		return dtolist;
//		
//	}
//
//	@Override
//	public void deleteUser(Integer id) {
//		User user=this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User", "Id", id));
//		 this.userRepo.delete(user);
//	}

	private User DtoToUser(UserDto userDto) {
		return this.modelMapper.map(userDto, User.class);
	}

	private UserDto userToDto(User user) {
		 return this.modelMapper.map(user, UserDto.class);
		}

	@Override
	public List<UserDto> getAllusers() {
		List<User> users=this.userRepo.findAll();
		List<UserDto> userdtos = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		
		return userdtos;
	}

	@Override
	public void deleteUser(Integer id) {
		User user=userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User", "id", id));
		this.userRepo.delete(user);
	}

	@Override
	public UserDto getUserById(Integer id) {
		User user = userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User", "ID", id));
		UserDto userDto=this.userToDto(user);
		return userDto;
	}
}
