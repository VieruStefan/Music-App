package com.pos.spotify.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CorsFilter extends OncePerRequestFilter {
    public void doFilterInternal(
                             @NonNull HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull FilterChain filterChain)
            throws ServletException, IOException {
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS");
            response.addHeader("Access-Control-Allow-Headers", "Authorization,DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Content-Range,Range");

        filterChain.doFilter(request, response);
    }
}