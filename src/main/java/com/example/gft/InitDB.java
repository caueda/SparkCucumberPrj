package com.example.gft;

import com.example.gft.model.User;
import com.example.gft.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
public class InitDB implements CommandLineRunner {
    private UserRepository userRepository;

    public InitDB(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.saveAndFlush(new User(1L, "Jhon","Mayer", new Date()));
        userRepository.saveAndFlush(new User(2L, "Thor","Spielberg", new Date()));
        userRepository.saveAndFlush(new User(3L, "Wolverine","Logan", new Date()));
    }
}
