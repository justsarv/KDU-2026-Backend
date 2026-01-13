package com.example.hospitalshift.service;

import com.example.hospitalshift.repository.UserRepository;
import com.example.hospitalshift.dto.UserRequestDTO;
import com.example.hospitalshift.dto.UserResponse;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(UserRequestDTO dto) {
        userRepository.createUser(dto);
    }

    public List<UserResponse> getUser(String username) {
        return userRepository.getUser(username);
    }

    public void updateUsername(String oldName, String newName) {
        userRepository.updateUser(oldName, newName);
    }
}