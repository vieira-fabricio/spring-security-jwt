package com.spring.security.jwt.controller;

import com.spring.security.jwt.dto.AuthenticationDto;
import com.spring.security.jwt.dto.LoginResponseDTO;
import com.spring.security.jwt.model.User;
import com.spring.security.jwt.dto.registerDTO;
import com.spring.security.jwt.repository.UserRepository;
import com.spring.security.jwt.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.userName(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid registerDTO data){
        if (this.userRepository.findByUserName(data.userName()) != null) return ResponseEntity.badRequest().build();

        String passwordEncripted = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.userName(), passwordEncripted, data.role());
        System.out.println(newUser);
        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
