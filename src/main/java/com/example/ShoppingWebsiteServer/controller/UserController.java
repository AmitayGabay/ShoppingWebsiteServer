package com.example.ShoppingWebsiteServer.controller;

import com.example.ShoppingWebsiteServer.model.CustomUser;
import com.example.ShoppingWebsiteServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public CustomUser createUser(@RequestBody CustomUser user) {
        return userService.register(user);
    }

    @DeleteMapping(value = "/delete-user", params = "id")
    public String deleteUser(@RequestParam Integer id) {
        return userService.deleteUserById(id);
    }

    @GetMapping(params = "id")
    public CustomUser getUserById(@RequestParam Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping(params = "username")
    public CustomUser getUserByUsername(@RequestParam String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping(params = "email")
    public CustomUser getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping(value = "/all-users")
    public List<CustomUser> getAllUsers() {
        return userService.getAllUsers();
    }
}
