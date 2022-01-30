package com.example.gft.controller;

import com.example.gft.mapper.UserMapper;
import com.example.gft.model.User;
import com.example.gft.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    UserMapper userMapper;

    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(CustomizedResponseEntityExceptionHandler.class)
                .build();
        ReflectionTestUtils.setField(userController, "userMapper", UserMapper.INSTANCE);
    }

    @Test
    void findAll() throws Exception {
        when(userService.findAll()).thenReturn(List.of(
                User.builder()
                        .name("Bono")
                        .surname("Vox")
                        .birthDate(new Date())
                        .build(),
                User.builder()
                        .name("Wolverine")
                        .surname("Vox")
                        .birthDate(new Date())
                        .build())
        );
        mockMvc.perform(get("/user").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.users[0].name", equalTo("Bono")))
                .andExpect(jsonPath("$.users[1].name", equalTo("Wolverine")))
        ;
    }

    @Test
    void findById() throws Exception {
        User userMock = User.builder()
                .name("Bono")
                .surname("Vox")
                .birthDate(new Date()).build();
        when(userService.findById(1L))
                .thenReturn(Optional.of(userMock));
        mockMvc.perform(get("/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Bono")))
        ;
    }

    @Test
    void findById_WhenNotFound_ThenReturn404() throws Exception {
        when(userService.findById(10L)).thenReturn(Optional.empty());

//        Exception exception = assertThrows(Exception.class, () -> {
//            mockMvc.perform(get("/user/10")
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .accept(MediaType.APPLICATION_JSON));
//        });
//
//        String expectedMessage = "User not found";
//        String actualMessage = exception.getMessage();
//        assertTrue(exception.getCause() instanceof NoSuchElementException);
//        assertTrue(actualMessage.contains(expectedMessage));

//        assertThatThrownBy(() -> mockMvc.perform(get("/user/10")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound())).hasCause(new NoSuchElementException());

        mockMvc.perform(get("/user/10")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    @Test
    void save() throws Exception {
        User user = new User(10L, "Bono", "Vox", new Date());
        user.setId(10L);
        when(userService.save(any(User.class))).thenReturn(user);
        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", containsString("/user/10")));
    }
}

