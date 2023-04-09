package com.example.ShoppingWebsiteServer.service;

import com.example.ShoppingWebsiteServer.model.User;

import java.util.List;

public interface UserServiceInterface {
    String register(User user);
    User signIn(Integer id);
    User signOut(Integer id);
    String deleteUserById(Integer id);
    User getUserById(Integer id);
    List<User> getAllUsers();
}
