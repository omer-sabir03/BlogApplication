package com.skillzamp.blogApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillzamp.blogApp.Exception.ApiException;
import com.skillzamp.blogApp.security.JwtTokenHelper;

import payload.JwtAuthRequest;
import payload.JwtAuthResponse;


@RestController

public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	@Autowired
	private UserDetailsService userDetailService;
	@Autowired
	private AuthenticationManager authManager;
	
	
		@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(
			@RequestBody JwtAuthRequest request) throws Exception{
	   authentication(request.getUsername(),request.getPassword());
	   UserDetails userDetail = userDetailService.loadUserByUsername(request.getUsername());
	   String token = jwtTokenHelper.generateToken(userDetail);
		JwtAuthResponse response=new JwtAuthResponse();
		response.setToken(token);
		return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
		
		}


		private void authentication(String username, String password) throws Exception {
			
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(username, password);
			
					
					try {
						authManager.authenticate(usernamePasswordAuthenticationToken);
					} catch (BadCredentialsException e) {
						e.printStackTrace();
						throw new ApiException("Invalid username & password!!");
					}
			
		}
}
