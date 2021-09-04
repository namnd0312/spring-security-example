package com.namnd.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.namnd.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	 Optional<User> findByUsername(String username);
	 Boolean existsByUsername(String username);
	 Boolean existsByEmail(String email);

}
