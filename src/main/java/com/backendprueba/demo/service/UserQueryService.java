package com.backendprueba.demo.service;

import com.backendprueba.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserQueryService {
    User findUserById(Long id);
    Page<User> findAllUsers(String firstName, Pageable pageable);
}
