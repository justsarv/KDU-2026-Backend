package com.example.api.auth;

import com.example.api.model.User;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserStore {
    private final Map<String, User> users = new ConcurrentHashMap<>();

    public Optional<User> findByUserName(String userName) {
        return Optional.ofNullable(users.get(userName));
    }

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public User save(User user) {
        users.put(user.getUserName(), user);
        return user;
    }
}
