package com.example.api.auth;

import com.example.api.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRegistrationService {
    private static final Logger log = LoggerFactory.getLogger(UserRegistrationService.class);

    private final UserStore userStore;
    private final PasswordEncoder passwordEncoder;

    public UserRegistrationService(UserStore userStore, PasswordEncoder passwordEncoder) {
        this.userStore = userStore;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(String userName, String rawPassword, String email, List<String> roles) {
        String hashed = passwordEncoder.encode(rawPassword);

        // Learning aid (optional): prove encoding
        log.info("Registering user={} storedPasswordHash={}", userName, hashed);

        User u = new User(userName, hashed, email, roles);
        return userStore.save(u);
    }
}
