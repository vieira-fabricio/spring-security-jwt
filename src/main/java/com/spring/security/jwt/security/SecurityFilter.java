package com.spring.security.jwt.security;

import com.spring.security.jwt.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;
    @Autowired
    UserRepository userRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.receverToken(request);
        if(token != null){
            var userName = tokenService.validateToken(token);
            System.out.println(userName.toString());
            UserDetails user = userRepository.findByUserName(userName);
            System.out.println(user.toString());
            var authentication = new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String receverToken(HttpServletRequest request) {
        var authReader = request.getHeader("Authorization");
        if (authReader == null) return null;
        return authReader.replace("Bearer ", "");
    }
}
