package com.example.gft.mapper;

import com.example.gft.dto.UserDTO;
import com.example.gft.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {
    UserMapper mapper = Mappers.getMapper(UserMapper.class);
    @BeforeEach
    void setUp() {
    }

    @Test
    void toDTO() {
        User user = new User(1L, "Jhon", "Smith", new Date());
        UserDTO userDTO = mapper.toDTO(user);
        assertEquals(user.getName(), userDTO.getName());
    }

    @Test
    void toEntity() {
        UserDTO userDTO = new UserDTO(0L, "Jhon", "Smith", new Date());
        User user = mapper.toEntity(userDTO);
        assertEquals(userDTO.getName(), user.getName());
    }
}