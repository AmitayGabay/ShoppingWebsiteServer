package com.example.ShoppingWebsiteServer.controller;

import com.example.ShoppingWebsiteServer.model.Item;
import com.example.ShoppingWebsiteServer.service.ItemService;
import com.example.ShoppingWebsiteServer.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/item")
public class ItemController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ItemService itemService;

    @PostMapping(value = "/add-to-favorites", params = "Authorization")
    public Item addToFavorites(@RequestParam(value = "Authorization") String token, @RequestBody Integer itemId) {
        String jwtToken = token.substring(7);
        String username = jwtUtil.extractUsername(jwtToken);
        return itemService.addToFavorites(username, itemId);
    }

    @DeleteMapping(value = "/remove-from-favorites", params = "Authorization")
    public String removeFromFavorites(@RequestParam(value = "Authorization") String token, @RequestBody Integer itemId) {
        String jwtToken = token.substring(7);
        String username = jwtUtil.extractUsername(jwtToken);
        return itemService.removeFromFavorites(username, itemId);
    }

    @GetMapping(value = "/get-favorites", params = "Authorization")
    public List<Item> getFavorites(@RequestParam(value = "Authorization") String token) {
        String jwtToken = token.substring(7);
        String username = jwtUtil.extractUsername(jwtToken);
        return itemService.getFavorites(username);
    }

    @GetMapping(value = "/favorites", params = {"Authorization", "name"})
    public List<Item> getFavoritesByName(@RequestParam(value = "Authorization") String token, @RequestParam String name) {
        String jwtToken = token.substring(7);
        String username = jwtUtil.extractUsername(jwtToken);
        return itemService.getFavoritesByName(username, name);
    }

    @GetMapping(value = "search", params = "id")
    public Item getItemById(@RequestParam Integer id) {
        return itemService.getItemById(id);
    }

    @GetMapping(value = "search", params = "name")
    public List<Item> getItemsByName(@RequestParam String name) {
        return itemService.getItemsByName(name);
    }

    @GetMapping(value = "/all-items")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping(value = "/all-favorites")
    public List<Item> getAllFavorites() {
        return itemService.getAllFavorites();
    }

}
