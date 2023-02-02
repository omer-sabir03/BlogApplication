package com.skillzamp.blogApp.services;

import java.util.List;

import payload.PostDTO;
import payload.PostResponse;

public interface PostServiceInterface {
		
	public PostDTO createPosts(PostDTO postDTO, Integer userId , Integer categoryId);
	
	public PostDTO updatePosts(PostDTO postDTO, Integer postId);
	
	public void deletePost(Integer postId);
	
	public PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortType);
	
	public PostDTO getSinglePost(Integer postId);
	
	List<PostDTO> getPostsByCategory(Integer categoryId);
	
	List<PostDTO> getPostsByUser(Integer userId);
	
	List<PostDTO> searchPost(String keyword);
	
	
}
