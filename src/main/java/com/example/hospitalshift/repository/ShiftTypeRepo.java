package com.example.hospitalshift.repository;

import com.example.hospitalshift.dto.ShiftTypeResponseDTO;
import com.example.hospitalshift.dto.ShiftTypeRequestDTO;
import com.example.hospitalshift.dto.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ShiftTypeRepo {
    private final JdbcTemplate jdbcTemplate;

    public ShiftTypeRepo(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    public void createShiftType(ShiftTypeRequestDTO shiftType){
        String sql="Insert into Shift_Type(name,description,tenant_id) VLAUES(?,?,?,?)";

        KeyHolder keyHolder=new GeneratedKeyHolder();
        jdbcTemplate.update(connection->
                {
                    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, shiftType.name());
                    ps.setString(2, shiftType.description());
                    ps.setLong(3, shiftType.tenantId());
                    return ps;
                }
                ,keyHolder
        );
    }

    public List<ShiftTypeResponseDTO> getShiftType(String name){
        String sql = """
            SELECT id, name, description, timezone, tenant_id 
            FROM Users 
            WHERE name = ?
            """;

        return jdbcTemplate.query(sql,shiftRowMapper(),name);
    }
    private RowMapper<ShiftTypeResponseDTO> shiftRowMapper() {
        return (rs, rowNum) -> new ShiftTypeResponseDTO(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getBoolean("isActive"),
                rs.getLong("tenant_id")
        );
    }
}
