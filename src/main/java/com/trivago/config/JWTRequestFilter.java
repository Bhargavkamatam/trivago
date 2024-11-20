package com.trivago.config;

import com.trivago.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JWTRequestFilter extends OncePerRequestFilter {
private JWTService jwsService;

    public JWTRequestFilter(JWTService jwsService) {

        this.jwsService = jwsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       String tokenHeader = request.getHeader("Authorization");
       if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
           String token = tokenHeader.substring(8, tokenHeader.length() - 1);
           String username;
           username = jwsService.getUserName(token);

           System.out.println(username);

       }
        filterChain.doFilter(request, response);
    }
}
