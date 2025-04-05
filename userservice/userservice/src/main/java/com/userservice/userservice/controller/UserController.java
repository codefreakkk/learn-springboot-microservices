package com.userservice.userservice.controller;

import java.util.List;

import com.userservice.userservice.LoginDTO;
import com.userservice.userservice.config.JwtService;
import com.userservice.userservice.repository.UserRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.userservice.userservice.models.User;
import com.userservice.userservice.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) throws Exception {
        userService.createUser(user);
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> signUp(@RequestBody LoginDTO userDTO) throws Exception {
        User user = User.builder()
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .build();

        userRepository.save(user);

        return new ResponseEntity<>("Sign Up Successful", HttpStatus.CREATED);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );
        User user = userRepository.findByEmail(loginDTO.getEmail());
        String token = jwtService.generateToken(user);

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<?> getUserById(@PathVariable int id)  {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity<?> ratingHotelFallback(@PathVariable int id, Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>("Some error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
