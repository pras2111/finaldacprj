package com.shareBuddy.repository;

import org.springframework.data.repository.CrudRepository;

import com.shareBuddy.pojos.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findByName(String name);
}
