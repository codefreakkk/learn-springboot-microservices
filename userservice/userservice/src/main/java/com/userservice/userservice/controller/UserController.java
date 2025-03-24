package com.userservice.userservice.controller;

import java.util.List;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.userservice.userservice.models.User;
import com.userservice.userservice.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

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
