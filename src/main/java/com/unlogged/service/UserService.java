package com.unlogged.service;

import com.unlogged.entity.User;
import com.unlogged.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    // Create a new user
    public User createUser(User user) {
        return userRepo.save(user);
    }

    // Read a user by ID
    public Optional<User> getUserById(int userId) {
        return userRepo.findById(userId);
    }

    // Read all users
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    // Update a user
    public User updateUser(User user) {
        if (userRepo.existsById(user.getUserId())) {
            return userRepo.save(user);
        } else {
            throw new IllegalArgumentException("User not found with id: " + user.getUserId());
        }
    }

    // Delete a user by ID
    public Boolean deleteUserById(int userId) {
        if (userRepo.existsById(userId)) {
            userRepo.deleteById(userId);
            return true;
        }
        return false;
    }
}
