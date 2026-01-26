package com.example.api.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger log = LoggerFactory.getLogger(RestAccessDeniedHandler.class);
    private final ObjectMapper om = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String user = (auth != null) ? auth.getName() : "anonymous";

            // Exercise 2 requirement: WARN on forbidden attempts
            log.warn("FORBIDDEN attempt user={} path={}", user, request.getRequestURI());

            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            om.writeValue(response.getWriter(), Map.of(
                    "error", "Forbidden",
                    "message", "You do not have permission to access this resource",
                    "path", request.getRequestURI()
            ));
        } catch (Exception ignored) {}
    }
}
