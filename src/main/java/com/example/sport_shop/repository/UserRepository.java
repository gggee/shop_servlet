package com.example.sport_shop.repository;

import com.example.sport_shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findByEmailContaining(String email);

    List<User> findByCity(String city);
}
