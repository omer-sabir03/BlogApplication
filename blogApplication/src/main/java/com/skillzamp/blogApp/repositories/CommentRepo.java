package com.skillzamp.blogApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillzamp.blogApp.models.Comment;


public interface CommentRepo extends JpaRepository <Comment, Integer>{

}
