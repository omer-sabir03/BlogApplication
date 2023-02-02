package com.skillzamp.blogApp.models;

import javax.annotation.Generated; 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer commentId;
	private String content;
	@ManyToOne
	private Posts post;
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Posts getPost() {
		return post;
	}
	public void setPost(Posts post) {
		this.post = post;
	}
	public Comment(Integer commentId, String content, Posts post) {
		super();
		this.commentId = commentId;
		this.content = content;
		this.post = post;
	}
	public Comment() {
		super();
	}
	
	
}
