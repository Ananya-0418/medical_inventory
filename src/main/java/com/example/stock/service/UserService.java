package com.example.stock.service;

import com.example.stock.dao.model.User;
import com.example.stock.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElse(null); // Return null if not found
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> createUsers(List<User> users) {
        return userRepository.saveAll(users);
    }


    public User updateUser(String userId, User userDetails) {
        Optional<User> existingUserOptional = userRepository.findById(userId);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.setName(userDetails.getName());
            existingUser.setRole(userDetails.getRole());
            existingUser.setEmail(userDetails.getEmail());
            return userRepository.save(existingUser);
        }
        return null;
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
