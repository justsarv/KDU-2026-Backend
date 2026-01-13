package com.example.hospitalshift.controller;

import com.example.hospitalshift.dto.ShiftTypeRequestDTO;
import com.example.hospitalshift.dto.ShiftTypeResponseDTO;
import com.example.hospitalshift.dto.UserResponse;
import com.example.hospitalshift.repository.ShiftTypeRepo;
import com.example.hospitalshift.repository.UserRepository;
import com.example.hospitalshift.dto.UserRequestDTO;
import com.example.hospitalshift.service.ShiftTypeService;
import com.example.hospitalshift.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospital")
public class HospitalManagementController {

    private final UserService userService;
    private final ShiftTypeService shiftTypeService;


    public HospitalManagementController(UserService userService, ShiftTypeService shiftTypeService) {
        this.userService = userService;
        this.shiftTypeService = shiftTypeService;
    }



    @PostMapping("/users")
    public ResponseEntity<String> addUser(@RequestBody UserRequestDTO dto) {
        userService.create(dto);
        return ResponseEntity.ok("User created successfully");
    }

    @GetMapping("/users/{tenantId}")
    public ResponseEntity<List<UserResponse>> getUsers(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUser(username));
    }

    @PutMapping("/users/update-name")
    public ResponseEntity<String> updateUserName(@RequestParam String oldName, @RequestParam String newName) {
        userService.updateUsername(oldName, newName);
        return ResponseEntity.ok("Username updated");
    }



    @PostMapping("/shift-types")
    public ResponseEntity<String> addShiftType(@RequestBody ShiftTypeRequestDTO dto) {
        shiftTypeService.create(dto);
        return ResponseEntity.ok("Shift Type created successfully");
    }

    @GetMapping("/shift-types/{tenantId}")
    public ResponseEntity<List<ShiftTypeResponseDTO>> getShiftTypes(@PathVariable String name) {
        return ResponseEntity.ok(shiftTypeService.getShiftType(name));
    }
}
