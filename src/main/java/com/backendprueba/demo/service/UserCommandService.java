package com.backendprueba.demo.service;

import com.backendprueba.demo.model.User;
import com.backendprueba.demo.dto.ResponseMessage;

public interface UserCommandService {
    ResponseMessage saveUser(User user);
    ResponseMessage updateUser(Long id, User userDetails);
    ResponseMessage deleteUser(Long id);
}
