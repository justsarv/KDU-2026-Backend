package com.example.api.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationEntryPoint entryPoint;

    public JwtAuthFilter(JwtService jwtService,
                         UserDetailsService userDetailsService,
                         AuthenticationEntryPoint entryPoint) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.entryPoint = entryPoint;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String auth = request.getHeader("Authorization");
        if (auth != null && auth.startsWith("Bearer ")) {
            String token = auth.substring(7);

            try {
                Jws<Claims> jws = jwtService.parse(token);
                String username = jws.getPayload().getSubject();

                @SuppressWarnings("unchecked")
                List<String> roles = (List<String>) jws.getPayload().get("roles", List.class);

                var authorities = roles.stream().map(SimpleGrantedAuthority::new).toList();

                // set SecurityContext (this is what makes /api/** work)
                var authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (JwtException ex) {
                SecurityContextHolder.clearContext();
                // Exercise 3: invalid token => clean 401 JSON
                entryPoint.commence(request, response,
                        new org.springframework.security.core.AuthenticationException("Invalid token", ex) {});
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
