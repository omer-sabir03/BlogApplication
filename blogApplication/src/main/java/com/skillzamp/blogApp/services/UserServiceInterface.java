package com.skillzamp.blogApp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import payload.UserDTO;

@Service
public interface UserServiceInterface {
		
	
	UserDTO createUser(UserDTO userDTO);
	
	UserDTO updateUser(UserDTO userDTO,int id);
	UserDTO getUserById(int Id);
	List<UserDTO> getAllUsers();
	
	void deleteUser(int id);
}
