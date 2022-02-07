package com.example.gft.service;

import com.example.gft.model.User;
import com.example.gft.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.StringJoiner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;

import rx.Observable;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

//    @Captor
//    ArgumentCaptor<Sort> sortArgumentCaptor;

    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void save() {
        userService.save(new User());
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }

    @Test
    void findAll() {
        int expectedCollectionSize = 2;
        ArgumentCaptor<Sort> sortArgumentCaptor = ArgumentCaptor.forClass(Sort.class);
        Mockito.when(userRepository.findAll(sortArgumentCaptor.capture())).thenReturn(List.of(new User(), new User()));
        Sort sortExpected = Sort.by("name");
        assertEquals(expectedCollectionSize, userService.findAll().size());
        assertThat(sortArgumentCaptor.getValue()).isEqualTo(sortExpected);
    }

    @Test
    void stringJoiner() {
        StringJoiner stringJoiner = new StringJoiner(", ");
        stringJoiner.add("One");
        stringJoiner.add("Two");
        assertEquals("One, Two", stringJoiner.toString());
    }

    @Test
    void testRxJava() {
        Observable.range(1, 10).subscribe(
                System.out::println
        );
    }

}