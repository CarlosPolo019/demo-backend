package com.backendprueba.demo.service;

import com.backendprueba.demo.model.User;
import com.backendprueba.demo.repository.UserRepository;
import com.backendprueba.demo.exception.EmailAlreadyExistsException;
import com.backendprueba.demo.exception.UserNotFoundException;
import com.backendprueba.demo.service.UserCommandService;
import com.backendprueba.demo.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("unused")
@Service
public class UserCommandServiceImpl implements UserCommandService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseMessage saveUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException("Email already registered: " + user.getEmail());
        }
        userRepository.save(user);
        return new ResponseMessage("User created successfully.");
    }

    @Override
    public ResponseMessage updateUser(Long id, User userDetails) {
        User existingUser = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
        
        existingUser.setFirstName(userDetails.getFirstName());
        existingUser.setLastName(userDetails.getLastName());
        existingUser.setEmail(userDetails.getEmail());
        existingUser.setBirthDate(userDetails.getBirthDate());
        existingUser.setUpdatedBy(userDetails.getUpdatedBy());
        existingUser.setUpdatedAt(userDetails.getUpdatedAt());

        userRepository.save(existingUser);
        return new ResponseMessage("User updated successfully.");
    }

    @Override
    public ResponseMessage deleteUser(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
        
        userRepository.delete(user);
        return new ResponseMessage("User deleted successfully.");
    }
}
