//package com.example.api.auth;
//
//import com.example.api.model.User;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//import java.util.Optional;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Component
//public class InMemoryUserStore implements UserStore {
//
//    private final Map<String, User> users = new ConcurrentHashMap<>();
//
//    @Override
//    public Optional<User> findByUserName(String userName) {
//        return Optional.ofNullable(users.get(userName));
//    }
//
//    @Override
//    public User save(User user) {
//        users.put(user.getUserName(), user);
//        return user;
//    }
//}
