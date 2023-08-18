package com.example.gft.repository;

import com.example.gft.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    private void test() {
        System.out.println("It is just a test");
    }
}
