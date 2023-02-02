package com.skillzamp.blogApp.services;

import payload.CommentDTO;

public interface CommentServiceInterface {
		
	CommentDTO createComment(CommentDTO commentDTO, Integer postId);
	
	void deleteComment(Integer commentId);
	
	CommentDTO updateComment(Integer commentId,CommentDTO commentDTO );
}
