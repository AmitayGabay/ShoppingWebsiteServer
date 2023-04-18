package com.example.ShoppingWebsiteServer.service;

import com.example.ShoppingWebsiteServer.model.User;

import java.util.List;

public interface UserServiceInterface {
    User register(User user);

    User signIn(String email);

    User signOut(Integer id);

    String deleteUserById(Integer id);

    User getUserById(Integer id);

    User getUserByEmail(String email);

    List<User> getAllUsers();
}
