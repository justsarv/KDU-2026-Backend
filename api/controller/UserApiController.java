package com.example.api.controller;

import com.example.api.auth.UserRegistrationService;
import com.example.api.auth.UserStore;
import com.example.api.controller.dto.CreateUserRequest;
import com.example.api.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserApiController {

    private final UserStore userStore;
    private final UserRegistrationService reg;

    public UserApiController(UserStore userStore, UserRegistrationService reg) {
        this.userStore = userStore;
        this.reg = reg;
    }

    // BASIC and ADMIN can view
    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('BASIC','ADMIN')")
    public Object me(Authentication auth) {
        return userStore.findByUserName(auth.getName())
                .map(u -> new Object() {
                    public final String userName = u.getUserName();
                    public final String email = u.getEmail();
                    public final List<String> roles = u.getRoles();
                })
                .orElseThrow();
    }

    // ADMIN can view all users
    @GetMapping("/admin/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> listUsers() {
        return userStore.findAll();
    }

    // ADMIN can add new users
    @PostMapping("/admin/users")
    @PreAuthorize("hasRole('ADMIN')")
    public User addUser(@RequestBody CreateUserRequest req) {
        return reg.register(req.userName(), req.password(), req.email(), req.roles());
    }
}
