package com.skillzamp.blogApp.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.skillzamp.blogApp.Exception.ResourceNotFoundException;
import com.skillzamp.blogApp.models.Category;
import com.skillzamp.blogApp.models.Posts;
import com.skillzamp.blogApp.models.Users;
import com.skillzamp.blogApp.repositories.CategoryRepo;
import com.skillzamp.blogApp.repositories.PostRepo;
import com.skillzamp.blogApp.repositories.UserRepo;
import com.skillzamp.blogApp.services.PostServiceInterface;

import payload.PostDTO;
import payload.PostResponse;

@Service
public class PostService implements PostServiceInterface {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	
	@Override
	public PostDTO createPosts(PostDTO postDTO ,Integer userId, Integer categoryId) {
		
		Users user=userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "UserId", userId));
		
		Category category=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
		
		
		Posts post=modelMapper.map(postDTO, Posts.class);
		post.setImageName("default.png");
		post.setDate(new Date());
		post.setCategory(category);
		post.setUsers(user);
		
		Posts newPost=postRepo.save(post);
		
		return modelMapper.map(newPost,PostDTO.class);
	}

	@Override
	public PostDTO updatePosts(PostDTO postDTO, Integer postId) {
		Posts post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Posts","postId",postId));
				post.setPostTitle(postDTO.getPostTitle());
				post.setPostContent(postDTO.getPostContent());
				post.setImageName(postDTO.getImageName());
				
				postRepo.save(post);
		return modelMapper.map(post,PostDTO.class);
	}

	@Override
	public void deletePost(Integer postId) {
	Posts post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Posts","postId",postId));
				postRepo.delete(post);
				
				
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortType) {
		
		
		Sort sort=(sortType.equalsIgnoreCase("asc"))?sort=Sort.by(sortBy).ascending(): (Sort.by(sortBy).descending());
	
		Pageable p=PageRequest.of(pageNumber, pageSize, sort);
		Page<Posts> pagePosts = postRepo.findAll(p);
		List<Posts> posts = pagePosts.getContent();
		
		List<PostDTO> postDTO=posts.stream().map((post)->modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
			
		PostResponse postResponse=new PostResponse();
		
		postResponse.setContent(postDTO);
		postResponse.setPageNumber(pagePosts.getNumber());
		postResponse.setPageSize(pagePosts.getSize());
		postResponse.setTotalPages(pagePosts.getTotalPages());
		postResponse.setTotalElements(pagePosts.getTotalElements());
		postResponse.setLastPage(pagePosts.isLast());
		
		
		
		return postResponse;
	
	}

	@Override
	public PostDTO getSinglePost(Integer postId) {
	Posts post=	postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Posts","PostId",postId));
	PostDTO postDTO=modelMapper.map(post,PostDTO.class);	
	return postDTO;
	}

	@Override
	public List<PostDTO> getPostsByCategory(Integer categoryId) {
		Category cat=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
		List<Posts> posts=postRepo.findByCategory(cat);
		
		List<PostDTO> postsDTO=posts.stream().map(post->modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
		return postsDTO;
	}

	@Override
	public List<PostDTO> getPostsByUser(Integer userId) {
		  Users user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("Users","UserId",userId));
		  
		  List<Posts> posts=postRepo.findByUsers(user);
		  			
		  		List<PostDTO> postsDTO=	posts.stream().map(post->modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());	  			
		  return postsDTO;
	}

	@Override
	public List<PostDTO> searchPost(String keyword) {
		List<Posts> posts = postRepo.findByPostTitleContaining(keyword);
		List<PostDTO> postDTO = posts.stream().map((post)->modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
		return postDTO;
	}

}
