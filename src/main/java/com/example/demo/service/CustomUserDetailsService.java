package com.example.demo.service;

import com.example.demo.model.AppUser;
import com.example.demo.repository.AppUserRepository;
// Внутрішній клас Spring Security, який представляє "стандартного" користувача
import org.springframework.security.core.userdetails.User;
// Інтерфейс, який описує, як виглядає користувач для системи безпеки (має методи getPassword(), getAuthorities() тощо)
import org.springframework.security.core.userdetails.UserDetails;
// Головний інтерфейс, який ми мусимо реалізувати, щоб Spring знав, де шукати юзерів
import org.springframework.security.core.userdetails.UserDetailsService; 
// Спеціальна помилка, яку треба кидати, якщо юзера не знайдено
import org.springframework.security.core.userdetails.UsernameNotFoundException;
// Анотація, щоб Spring створив екземпляр цього класу і тримав його в пам'яті
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AppUserRepository repository;

    public CustomUserDetailsService(AppUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return User.builder()
                .username(appUser.getUsername())
                .password(appUser.getPassword())
                .roles(appUser.getRole().replace("ROLE_", ""))
                .build();
    }
}
