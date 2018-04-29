package com.rasto.accommodationbookingsystem.backend.service;

import com.rasto.accommodationbookingsystem.backend.data.entity.User;

import javax.validation.constraints.NotNull;

public interface UserService extends CrudService<User> {
	/**
	 * Find user by email
	 * @param email
	 * @return true if user exists, else false
	 */
	boolean exists(@NotNull final String email);
}
