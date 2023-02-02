package com.skillzamp.blogApp.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private UserDetailsService userDetailService;
	@Autowired
	private JwtTokenHelper jwtHelper;
	String username=null;
	
	String token=null;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String requestToken=request.getHeader("Authorization");
		System.out.println(requestToken);
		
		
//		if(requestToken==null) {
//			System.out.println("Token Null");
		
	
		if(requestToken!=null && requestToken.startsWith("Bearer")) {
			token=requestToken.substring(7);
			try {
			username=jwtHelper.getUsernamefromToken(token);
			}catch(IllegalArgumentException e){
				System.out.println("Unable to Get Jwt Token");
			}
			catch(ExpiredJwtException e){
				e.printStackTrace();
			}
			catch(MalformedJwtException e){
				System.out.println("Unable to Get Jwt Token");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}else
			System.out.println("Jwt Token Does not Start with Bearer");
		
	
	       
	    
	
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				UserDetails userDetail=userDetailService.loadUserByUsername(username);
				
				if( jwtHelper.validateToken(token, userDetail))
				{
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetail, null,userDetail.getAuthorities());
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					
				}else {
					System.out.println("Invalid jwt token");
				}
}
		else {
			System.out.println("Username is null");
		}
		filterChain.doFilter(request, response);
}
}