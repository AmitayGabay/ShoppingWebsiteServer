package com.example.ShoppingWebsiteServer.repository;

import com.example.ShoppingWebsiteServer.model.User;

import java.util.List;

public interface UserRepositoryInterface {
    String register(User user);
    User signIn(Integer id);
    User signOut(Integer id);
    String deleteUserById(Integer id);
    User getUserById(Integer id);
    List<User> getAllUsers();
}
