package com.example.hospitalshift.service;

import com.example.hospitalshift.repository.ShiftTypeRepo;
import com.example.hospitalshift.dto.ShiftTypeRequestDTO;
import com.example.hospitalshift.dto.ShiftTypeResponseDTO;
import com.example.hospitalshift.repository.ShiftTypeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftTypeService {
    private final ShiftTypeRepo shiftTypeRepo;

    public ShiftTypeService(ShiftTypeRepo shiftTypeRepo) {
        this.shiftTypeRepo = shiftTypeRepo;
    }

    public void create(ShiftTypeRequestDTO dto) {
        shiftTypeRepo.createShiftType(dto);
    }

    public List<ShiftTypeResponseDTO> getShiftType(String name) {
        return shiftTypeRepo.getShiftType(name);
    }
}