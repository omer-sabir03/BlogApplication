package com.skillzamp.blogApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillzamp.blogApp.services.impl.CommentService;

import payload.ApiResponse;
import payload.CommentDTO;

@RestController
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@GetMapping("/posts/{postId}/comment")
	public ResponseEntity<CommentDTO> createComment(@PathVariable Integer postId, CommentDTO commentDTO){
		CommentDTO createComment = commentService.createComment(commentDTO, postId);
		return new ResponseEntity<CommentDTO>(createComment,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
		commentService.deleteComment(commentId);
		return new ResponseEntity<>(new ApiResponse ("Successfully Deleted", false),HttpStatus.OK);
	}
	
	@PutMapping("/comment/{commentId}")
	public ResponseEntity<CommentDTO> updateComment(Integer commentId, CommentDTO commentDTO){
		CommentDTO updateComment = commentService.updateComment(commentId, commentDTO);
		return new ResponseEntity<CommentDTO>(updateComment,HttpStatus.OK);
	}
}
