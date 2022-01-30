package com.example.gft.controller;

import com.example.gft.config.Config;
import com.example.gft.dto.UserDTO;
import com.example.gft.dto.UserDTOList;
import com.example.gft.mapper.UserMapper;
import com.example.gft.model.User;
import com.example.gft.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private UserMapper userMapper;
    private Config config;

    public UserController(UserService userService, UserMapper userMapper, Config config) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.config = config;
    }

    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserDTOList> findAll() {
        log.error("Profile test config.appName = " + config.getAppName() + " cpus: " + config.getCpus());
        List<User> users = userService.findAll();
        UserDTOList userDTOList = new UserDTOList(users);
        return new ResponseEntity<UserDTOList>(userDTOList, HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable("id") Long id) {
        Optional<User> returned = userService.findById(id);
//        return ResponseEntity.of(returned.map(userMapper::toDTO));
          return returned
                  .map(user -> new ResponseEntity(userMapper.toDTO(user), HttpStatus.OK))
                  .orElseThrow(() -> new NoSuchElementException("User not found."));
    }

    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody User user) {
        User savedUser = userService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
