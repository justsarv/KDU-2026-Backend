package com.example.booklibraryfinal.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.Signature;
import java.util.Date;
import java.util.function.Function;
@Component
public class JwtUtil {

    private static final String SECRET_KEY="5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    public String generateToken(String userName){
        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails){
        final String userName=extractUsername(token);
        return (userName.equalsIgnoreCase(userDetails.getUsername()) && !isTokeExpired(token));
    }

    private<T> T extractClaim(String token , Function<Claims,T> claimsResolver){
        final Claims claims=Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }

    private Key getSigningKey(){
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private boolean isTokeExpired(String token){
            return extractClaim(token,Claims::getExpiration).before(new Date());
    }
}
