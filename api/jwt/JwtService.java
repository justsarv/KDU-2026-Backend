package com.example.api.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.*;

@Service
public class JwtService {

    // For learning: keep it in code. In real apps put in env/config.
    private final SecretKey key = Keys.hmacShaKeyFor(
            "CHANGE_THIS_TO_A_LONG_RANDOM_SECRET_AT_LEAST_32_BYTES!".getBytes()
    );

    public String generateToken(String username, Collection<? extends GrantedAuthority> authorities) {
        List<String> roles = authorities.stream().map(GrantedAuthority::getAuthority).toList();

        Instant now = Instant.now();
        Instant exp = now.plusSeconds(60 * 60); // 1 hour

        return Jwts.builder()
                .subject(username)
                .issuedAt(Date.from(now))
                .expiration(Date.from(exp))
                .claim("roles", roles)
                .signWith(key)
                .compact();
    }

    public Jws<Claims> parse(String token) throws JwtException {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);
    }
}
