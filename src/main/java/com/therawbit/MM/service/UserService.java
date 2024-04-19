package com.therawbit.MM.service;

import com.therawbit.MM.dto.UserRegisterDTO;
import com.therawbit.MM.repository.UserRepository;
import com.therawbit.MM.entity.Status;
import com.therawbit.MM.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public void saveUser(User user) {
        user.setStatus(Status.ONLINE);
        repository.save(user);

    }

    public void disconnect(User user) {
        User storedUser = repository.findById(user.getUsername()).orElse(null);
        if (storedUser != null) {
            storedUser.setStatus(Status.OFFLINE);
            repository.save(storedUser);
        }
    }

    public List<User> findConnectedUsers() {
        return repository.findAllByStatus(Status.ONLINE);
    }

    public void registerUser(UserRegisterDTO registerDto) {
        User user = repository.findByUsername(registerDto.getUsername()).orElse(null);
        if (user != null) {
            throw new RuntimeException("User already exists.");
        }
        user = new User();
        user.setUsername(registerDto.getUsername());
        user.setFullName(registerDto.getFullName());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        repository.save(user);
    }
}

