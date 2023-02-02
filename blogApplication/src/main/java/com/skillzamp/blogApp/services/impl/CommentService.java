package com.skillzamp.blogApp.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillzamp.blogApp.Exception.ResourceNotFoundException;
import com.skillzamp.blogApp.models.Comment;
import com.skillzamp.blogApp.models.Posts;
import com.skillzamp.blogApp.repositories.CommentRepo;
import com.skillzamp.blogApp.repositories.PostRepo;
import com.skillzamp.blogApp.services.CommentServiceInterface;

import payload.CommentDTO;

@Service
public class CommentService implements CommentServiceInterface {

	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public CommentDTO createComment(CommentDTO commentDTO, Integer postId) {
		Posts post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Posts" ,"postId", postId));
		Comment comment = modelMapper.map(commentDTO,Comment.class);
	    comment.setPost(post);
	    Comment saveComment = commentRepo.save(comment);
	    return modelMapper.map(saveComment,CommentDTO.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
	Comment comment = commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","commentId",commentId));
			commentRepo.delete(comment);
	}

	@Override
	public CommentDTO updateComment(Integer commentId, CommentDTO commentDTO) {
		Comment comment = commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","commentId",commentId));
		comment.setContent(commentDTO.getContent());
		return modelMapper.map(comment,CommentDTO.class);
	}

}
