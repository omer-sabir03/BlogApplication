package com.skillzamp.blogApp.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skillzamp.blogApp.config.AppConstants;
import com.skillzamp.blogApp.services.impl.PostService;

import payload.ApiResponse;
import payload.PostDTO;
import payload.PostResponse;

@RestController
public class PostController {
		
	@Autowired
	private PostService postService;
	
	@PostMapping("/users/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO,@PathVariable Integer userId, @PathVariable Integer categoryId ){
		PostDTO  savedPost=postService.createPosts(postDTO, userId, categoryId);
		return new ResponseEntity<>(savedPost,HttpStatus.OK) ;
	}
	
	@GetMapping("/user/{userId}/posts")
	
	public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable Integer userId){
		List<PostDTO> postDTO=postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDTO>>(postDTO,HttpStatus.OK);
	}
	
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDTO>> getPostByCategory(@PathVariable Integer categoryId){
		List<PostDTO> posts=postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDTO>>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value="pageNumber", defaultValue=AppConstants.pageNumber,required=false )Integer pageNumber,
			@RequestParam(value="pageSize", defaultValue=AppConstants.pageSize, required=false)Integer pageSize,
			@RequestParam(value="sortBy", defaultValue=AppConstants.sortBY, required=false)String sortBy,
			@RequestParam(value="sortType" ,defaultValue=AppConstants.sortType , required=false)String sortType){
		
	 PostResponse Posts = postService.getAllPosts(pageNumber,pageSize,sortBy,sortType);
		
		return new ResponseEntity<PostResponse>(Posts,HttpStatus.OK);
	}
	
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDTO> getSinglePose(@PathVariable Integer postId){
		PostDTO singlePost = postService.getSinglePost(postId);
		return new ResponseEntity<PostDTO>(singlePost,HttpStatus.OK);
	}
	
	@DeleteMapping("/post/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
		postService.deletePost(postId);
		return new ResponseEntity<>(new ApiResponse("Deleted Successfully",true),HttpStatus.OK);
	}
	
	
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO,@PathVariable Integer postId){
		
		PostDTO updatedPost = postService.updatePosts(postDTO, postId);
		return new ResponseEntity<PostDTO>(updatedPost,HttpStatus.OK);
	}
	
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDTO>> searchposts(String keyword){
		List<PostDTO> searchPost = postService.searchPost(keyword);
		return new ResponseEntity<List<PostDTO>>(searchPost,HttpStatus.OK);
	}
	
	
}
