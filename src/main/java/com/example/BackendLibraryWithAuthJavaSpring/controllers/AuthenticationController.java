package com.example.BackendLibraryWithAuthJavaSpring.controllers;

import com.example.BackendLibraryWithAuthJavaSpring.domain.User;
import com.example.BackendLibraryWithAuthJavaSpring.dtos.AuthenticationDTO;
import com.example.BackendLibraryWithAuthJavaSpring.dtos.LoginResponseDTO;
import com.example.BackendLibraryWithAuthJavaSpring.dtos.RegisterDTO;
import com.example.BackendLibraryWithAuthJavaSpring.repositories.UserRepository;
import com.example.BackendLibraryWithAuthJavaSpring.services.TokenService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
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
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var user = (User) auth.getPrincipal();
        var token = tokenService.generateToken((User) auth.getPrincipal());
        var role = getRoleByName(user.getLogin());

        return ResponseEntity.ok(new LoginResponseDTO(token, role));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if(this.userRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());
        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
    public String getRoleByName(String name) {
        User user = userRepository.findUserRoleByLogin(name);
        if(user != null) {
            return user.getRole().getRole();
        } else {
            throw new IllegalArgumentException("User not found using " + name + " as a login.");
        }
    }
}
