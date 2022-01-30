package com.example.gft.service;

import com.example.gft.model.User;
import com.example.gft.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll(Sort.by("name"));
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
