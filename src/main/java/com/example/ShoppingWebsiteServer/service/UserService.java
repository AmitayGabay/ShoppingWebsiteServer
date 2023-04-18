package com.example.ShoppingWebsiteServer.service;

import com.example.ShoppingWebsiteServer.model.User;
import com.example.ShoppingWebsiteServer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(User user) {
        if (user.getFirstName() == null || user.getLastName() == null || user.getEmail() == null || user.getAddress() == null) {
            System.out.println("User not created, first name, last name, email and address are required");
            return null;
        }
        return userRepository.register(user);
    }

    @Override
    public User signIn(String email) {
        if (email == null) {
            System.out.println("email is required");
            return null;
        }
        User registeredUser = userRepository.getUserByEmail(email);
        if (registeredUser == null) {
            System.out.println("The user with this email does not exist");
            return null;
        }
        if (registeredUser.getIsConnected()) {
            System.out.println("The user with this email is already connected");
            return null;
        }
        return userRepository.signIn(email);
    }

    @Override
    public User signOut(Integer id) {
        if (id == null) {
            System.out.println("id is required");
            return null;
        }
        User registeredUser = userRepository.getUserById(id);
        if (registeredUser == null) {
            System.out.println("The user with this id does not exist");
            return null;
        }
        if (!registeredUser.getIsConnected()) {
            System.out.println("The user with this id does not connected");
            return null;
        }
        return userRepository.signOut(id);
    }

    @Override
    public String deleteUserById(Integer id) {
        if (id == null) {
            return "It is not possible to delete the user without id";
        }
        User registeredUser = userRepository.getUserById(id);
        if (registeredUser == null) {
            return "The user with this id does not exist, so it cannot be deleted";
        }
        return userRepository.deleteUserById(id);
    }

    @Override
    public User getUserById(Integer id) {
        if (id == null) {
            System.out.println("It is not possible to accept the user without id");
            return null;
        }
        return userRepository.getUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            System.out.println("It is not possible to accept the user without email");
            return null;
        }
        return userRepository.getUserByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
}
