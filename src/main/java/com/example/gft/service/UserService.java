package com.example.gft.service;

import com.example.gft.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);

    List<User> findAll();

    Optional<User> findById(Long id);
}
