package com.flight.demo.controller;

import com.flight.demo.dto.AuthResponseDto;
import com.flight.demo.dto.LoginDto;
import com.flight.demo.dto.UserDto;
import com.flight.demo.model.Role;
import com.flight.demo.model.User;
import com.flight.demo.repository.RoleRepository;
import com.flight.demo.repository.UserRepository;
import com.flight.demo.security.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Set;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private TokenGenerator tokenGenerator;


    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, TokenGenerator tokenGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenGenerator = tokenGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto){
        if(userRepository.existsByUsername(userDto.getUsername())){
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsByEmail(userDto.getEmail())){
            return new ResponseEntity<>("Email is taken!", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        if(userDto.getPassword().equals(userDto.getConfirmPassword())){
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        } else {
            return new ResponseEntity<>("Passwords do not match!", HttpStatus.BAD_REQUEST);
        }
        user.setEmail(userDto.getEmail());
        Role roles = roleRepository.findByName("USER").get();
        user.setRole(roles.getName());
        userRepository.save(user);
        return new ResponseEntity<>("User successfully registered!", HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        System.out.println("Authentication: " + authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
    }
}
