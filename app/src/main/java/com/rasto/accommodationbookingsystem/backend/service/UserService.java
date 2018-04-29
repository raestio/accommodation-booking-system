package com.rasto.accommodationbookingsystem.backend.service;

import com.rasto.accommodationbookingsystem.backend.data.entity.User;

import java.util.Optional;

public interface UserService extends CrudService<User> {

	/**
	 * Find user by email
	 * @param email
	 * @return true if user exists, else false
	 */
	boolean exists(String email);

	/**
	 * Find user by email
	 * @param email
	 */
	Optional<User> findByEmail(String email);
}
