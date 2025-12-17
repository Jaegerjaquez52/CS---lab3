package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.demo.model.AppUser;
import com.example.demo.repository.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Lab3Application {
    public static void main(String[] args) {
        SpringApplication.run(Lab3Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(AppUserRepository repository, PasswordEncoder encoder) {
        return (args) -> {
            // Створюємо Admim
            AppUser admin = new AppUser();
            admin.setUsername("admin");
            admin.setPassword(encoder.encode("admin")); 
            admin.setRole("ROLE_ADMIN");
            repository.save(admin);

            // Створюємо User
            AppUser user = new AppUser();
            user.setUsername("user");
            user.setPassword(encoder.encode("user"));
            user.setRole("ROLE_USER");
            repository.save(user);
        };
    }
}