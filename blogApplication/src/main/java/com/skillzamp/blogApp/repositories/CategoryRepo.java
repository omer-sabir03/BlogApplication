package com.skillzamp.blogApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillzamp.blogApp.models.Category;
import com.skillzamp.blogApp.models.Posts;
import com.skillzamp.blogApp.models.Users;

public interface CategoryRepo extends JpaRepository<Category,Integer>{
	    
		
}
