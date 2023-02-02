package com.skillzamp.blogApp.services.impl;

import java.util.List; 
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillzamp.blogApp.Exception.ResourceNotFoundException;
import com.skillzamp.blogApp.models.Users;
import com.skillzamp.blogApp.repositories.UserRepo;
import com.skillzamp.blogApp.services.UserServiceInterface;

import payload.UserDTO;

@Service
public class UserService implements UserServiceInterface {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDTO createUser(UserDTO userDTO) {
		
		Users user=dtoToUsers(userDTO);
		Users savedUser=userRepo.save(user);
		return userToUserDTO(savedUser);
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO, int id) {
		Users user=userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Users","id",id));
		
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setAbout(userDTO.getAbout());
		user.setPassword(userDTO.getPassword());
		
		 Users updatedUser= userRepo.save(user);
		
		return  userToUserDTO(updatedUser);
	}

	@Override
	public UserDTO getUserById (int id) {
		Users user= userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Users","id",id));
		return userToUserDTO(user);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<Users> users=userRepo.findAll();
		
		List<UserDTO> collect= users.stream().map(user->userToUserDTO(user)).collect(Collectors.toList());
		return collect;
	}

	@Override
	public void deleteUser(int id) {
		
	
			Users user = userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Users","id",id));
			userRepo.delete(user);
			
		
		

	}
	
     private Users dtoToUsers(UserDTO userDTO) {
    	 Users user=modelMapper.map(userDTO, Users.class);
//    	 user.setId(userDTO.getId());
//    	 user.setName(userDTO.getName());
//    	 user.setEmail(userDTO.getEmail());
//    	 user.setPassword(userDTO.getEmail());
//    	 user.setAbout(userDTO.getAbout());
    	 return user;
     }
     
     private UserDTO userToUserDTO(Users user) {
    	 UserDTO userDTO=modelMapper.map(user, UserDTO.class);
//    	 userDTO.setName(user.getName());
//    	 userDTO.setId(user.getId());
//    	  userDTO.setAbout(user.getAbout());
//    	  userDTO.setEmail(user.getEmail());
//    	  userDTO.setPassword(user.getPassword());
    	  
    	  
    	 return userDTO;
    	 
     }

}
