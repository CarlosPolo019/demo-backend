package com.backendprueba.demo.controller;

import com.backendprueba.demo.model.User;
import com.backendprueba.demo.service.UserQueryService;
import com.backendprueba.demo.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/users")
public class UserQueryController {

    @Autowired
    private UserQueryService userQueryService;

    // Fetch all users with optional filtering by firstName
    @GetMapping
    public ResponseEntity<?> getAllUsers(
        @RequestParam(required = false) String firstName,
        Pageable pageable) {
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
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(ex.getMessage())); 
        }
    }
}
