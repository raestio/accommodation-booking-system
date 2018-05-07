package com.rasto.accommodationbookingsystem.backend.service.impl;

import com.rasto.accommodationbookingsystem.backend.data.entity.User;
import com.rasto.accommodationbookingsystem.backend.repository.UserRepository;
import com.rasto.accommodationbookingsystem.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Find user by email
     * @param email
     * @return true if user exists, else false
     */
    @Override
    public boolean exists(String email) {
        return userRepository.findByEmail(email.toLowerCase()).isPresent();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User createNew() {
        return new User();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public User saveOrUpdate(User entity) {
        return userRepository.saveAndFlush(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(User userToDelete) {
        if (userToDelete == null) {
            throw new EntityNotFoundException();
        }
        userRepository.delete(userToDelete);
    }
}
