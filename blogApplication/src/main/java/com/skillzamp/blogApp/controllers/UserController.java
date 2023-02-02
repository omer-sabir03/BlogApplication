package com.skillzamp.blogApp.controllers;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skillzamp.blogApp.services.impl.UserService;

import javax.validation.Valid;
import payload.ApiResponse;
import payload.UserDTO;

@RestController
public class UserController {
		
		@Autowired
		private UserService userService;
	
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDto){
		UserDTO userDTO=userService.createUser(userDto);
		return ResponseEntity.ok( userDTO);
	}
	
	
	@RequestMapping(value="/user/{id}",method=RequestMethod.PUT)
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO,@PathVariable int id){
		UserDTO updatedUser=userService.updateUser(userDTO, id);
		return ResponseEntity.ok(updatedUser);
	}
	
	@RequestMapping(value="/user/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable int id){
		UserDTO user=userService.getUserById( id);
		return ResponseEntity.ok(user);
	}
	
	@RequestMapping(value="/user")
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value="/user/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable int id){
		userService.deleteUser(id);
		return new ResponseEntity<>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
	}
	
	
}
