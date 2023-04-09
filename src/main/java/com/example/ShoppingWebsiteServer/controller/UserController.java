package com.example.ShoppingWebsiteServer.controller;

import com.example.ShoppingWebsiteServer.model.User;
import com.example.ShoppingWebsiteServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public String createUser(@RequestBody User user) {
        return userService.register(user);
    }

    @PutMapping(value = "/sign-in", params = "id")
    public User signIn(@RequestParam Integer id) {
        return userService.signIn(id);
    }

    @PutMapping(value = "/sign-out", params = "id")
    public User signOut(@RequestParam Integer id) {
        return userService.signOut(id);
    }

    @DeleteMapping(value = "/delete-user", params = "id")
    public String deleteUser(@RequestParam Integer id) {
        return userService.deleteUserById(id);
    }

    @GetMapping(params = "id")
    public User getUserById(@RequestParam Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping(value = "/all-users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
