package com.example.demo.repository;

import com.example.demo.model.AppUser;
// Бібліотека Spring Data JPA (Це головний інтерфейс, який дає методи save, findAll тощо)
import org.springframework.data.jpa.repository.JpaRepository;
// Стандартна бібліотека Java 
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username); // Пошук для логіну
}
