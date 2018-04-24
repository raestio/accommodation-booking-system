package com.rasto.accommodationbookingsystem.backend.service;


import com.rasto.accommodationbookingsystem.backend.data.entity.User;
import com.rasto.accommodationbookingsystem.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Service
public class UserService implements CrudService<User> {

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserRepository getRepository() {
		return userRepository;
	}

	@Override
	@Transactional
	public void delete(User userToDelete) {
		CrudService.super.delete(userToDelete);
	}

	@Override
	public User createNew() {
		return new User();
	}

	/**
	 * Find user by email
	 * @param email
	 * @return true if user exists, else false
	 */
	public boolean exists(@NotNull final String email) {
		return getRepository().findByEmail(email.toLowerCase()).isPresent();
	}
}
