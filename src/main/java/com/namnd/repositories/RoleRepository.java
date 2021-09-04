package com.namnd.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.namnd.models.Role;
import com.namnd.models.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	  Optional<Role> findByName(RoleName roleName);
}
