package com.example.stock.service.controller;

import com.example.stock.dao.model.User;
import com.example.stock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Get a user by ID
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        User user = userService.getUserById(userId);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    // Create a new user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/users")
    public ResponseEntity<List<User>> createUsers(@RequestBody List<User> users) {
        List<User> createdUsers = userService.createUsers(users);
        return ResponseEntity.ok(createdUsers);
    }


    // Update a user
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable String userId, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(userId, userDetails);
        return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
    }

    // Delete a user
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
