package com.skillzamp.blogApp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillzamp.blogApp.models.Users;

public interface UserRepo extends JpaRepository<Users,Integer> {
     Optional<Users> findByEmail(String email);
}
