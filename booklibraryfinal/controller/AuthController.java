package com.example.booklibraryfinal.controller;

import com.example.booklibraryfinal.exception.UserNameNotFoundExceptionMINE;
import com.example.booklibraryfinal.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil, AuthenticationManager authenticationManager){
        this.jwtUtil=jwtUtil;
        this.authenticationManager=authenticationManager;
    }

    @PostMapping("/login")
    Map<String, String> login(@RequestBody Map<String,String> request){
        Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.get("username"),request.get("password"))
        );
        if(authentication.isAuthenticated()){
            String token=jwtUtil.generateToken(request.get("username"));
            return Map.of("token",token);
        }
        else{
            throw new UserNameNotFoundExceptionMINE(request.get("username"));
        }
    }

}
