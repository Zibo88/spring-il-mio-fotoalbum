package com.corsojava.album.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corsojava.album.model.User;

public interface UserRepository  extends JpaRepository<User, Integer>{
	Optional<User> findByUsername(String username);
}
