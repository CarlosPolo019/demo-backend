package com.backendprueba.demo.controller;

import com.backendprueba.demo.model.User;
import com.backendprueba.demo.service.UserCommandService;
import com.backendprueba.demo.service.UserQueryService;
import com.backendprueba.demo.exception.EmailAlreadyExistsException;
import com.backendprueba.demo.exception.UserNotFoundException;
import com.backendprueba.demo.dto.ResponseMessage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserCommandController {

    @Autowired
    private UserCommandService userCommandService;

    @Autowired
    private UserQueryService userQueryService;

    // Create a new user
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            ResponseMessage response = userCommandService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (EmailAlreadyExistsException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseMessage(ex.getMessage()));
        }
    }

    // Update a specific user by ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody User userDetails, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            ResponseMessage response = userCommandService.updateUser(id, userDetails);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(ex.getMessage()));
        }
    }

    // Delete a specific user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            ResponseMessage response = userCommandService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(ex.getMessage()));
        }
    }

    // Fetch all users with optional filtering by first name
    @GetMapping
    public ResponseEntity<?> getAllUsers(@RequestParam(required = false) String firstName, Pageable pageable) {
        Page<User> users = userQueryService.findAllUsers(firstName, pageable);
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseMessage("No users found."));
        }
        return ResponseEntity.ok(users);
    }

    // Fetch a specific user by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            User user = userQueryService.findUserById(id);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(ex.getMessage()));
        }
    }
}
