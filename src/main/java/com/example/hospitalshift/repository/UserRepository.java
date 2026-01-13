package com.example.hospitalshift.repository;

import com.example.hospitalshift.dto.UserRequestDTO;
import com.example.hospitalshift.dto.UserResponse;
import org.apache.catalina.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    public void createUser(UserRequestDTO user){
        String sql="Insert into Users(username,timezone,tenant_id) VALUES(?,?,?)";

        KeyHolder keyHolder=new GeneratedKeyHolder();
        jdbcTemplate.update(connection->
                {
                    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, user.username());
                    ps.setString(2, user.timezone());
                    ps.setLong(3, user.tenantId());
                    return ps;
                }
                ,keyHolder
        );
    }

    public List<UserResponse> getUser(String username){
        String sql = """
            SELECT id, username, is_logged_in, timezone, tenant_id 
            FROM Users 
            WHERE username = ?
            """;
        return jdbcTemplate.query(sql,userRowMapper(),username);
    }

    private RowMapper<UserResponse> userRowMapper(){
        return (rs, rowNum) ->new UserResponse(rs.getLong("id"),
                rs.getString("username"),
                rs.getBoolean("is_logged_in"),
                rs.getString("timezone"),
                rs.getLong("tenant_id")
        );
    }

    public void updateUser(String usernameOld,String usernameNew ){
        String sql= """
                Update Users SET username= ? where username=?
                """;
        jdbcTemplate.update(sql,usernameNew,usernameOld);
    }
}
