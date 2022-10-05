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
        return userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll(Sort.by("name"));
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.of(userRepository.findAllById(List.of(id))).map(
                l -> {
                    if(l.isEmpty()) return null;
                    else return l.get(0);
                }
        );
    }
}
