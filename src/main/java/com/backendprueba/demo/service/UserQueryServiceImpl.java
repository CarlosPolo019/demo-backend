package com.backendprueba.demo.service.impl;

import com.backendprueba.demo.model.User;
import com.backendprueba.demo.repository.UserRepository;
import com.backendprueba.demo.exception.UserNotFoundException;
import com.backendprueba.demo.service.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
    }

    @Override
    public Page<User> findAllUsers(String firstName, Pageable pageable) {
        if (firstName != null && !firstName.isEmpty()) {
            return userRepository.findByFirstNameContaining(firstName, pageable);
        } else {
            return userRepository.findAll(pageable);
        }
    }
}
