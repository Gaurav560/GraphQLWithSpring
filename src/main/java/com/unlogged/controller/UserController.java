package com.unlogged.controller;

import com.unlogged.entity.User;
import com.unlogged.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @MutationMapping
 public User createUser(@Argument String name, @Argument String email, @Argument String phone, @Argument String password){
       User user=new User();
       user.setName(name);
       user.setEmail(email);
       user.setPhone(phone);
       user.setPassword(password);
        return userService.createUser(user);
 }

@QueryMapping
 public List<User> getAllUsers(){
        return userService.getAllUsers();
 }

 @QueryMapping
 public Optional<User> getUserById(@Argument int userId){
      return  userService.getUserById(userId);
 }

 @MutationMapping
 public Boolean deleteUserById(@Argument int userId){
        return userService.deleteUserById(userId);
 }
}
