package com.example.ShoppingWebsiteServer.controller;

import com.example.ShoppingWebsiteServer.model.FavoriteRequest;
import com.example.ShoppingWebsiteServer.model.Item;
import com.example.ShoppingWebsiteServer.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping(value = "/add-to-favorites")
    public Item addToFavorites(@RequestBody FavoriteRequest favoriteRequest) {
        return itemService.addToFavorites(favoriteRequest);
    }

    @DeleteMapping(value = "/remove-from-favorites")
    public String removeFromFavorites(@RequestBody FavoriteRequest favoriteRequest) {
        return itemService.removeFromFavorites(favoriteRequest);
    }

    @GetMapping(value = "/get-favorites", params = "id")
    public List<Item> getFavoritesByUserId(@RequestParam Integer id) {
        return itemService.getFavoritesByUserId(id);
    }

    @GetMapping(value = "/favorites", params = {"userid", "name"})
    public List<Item> getFavoritesByName(@RequestParam Integer userid, @RequestParam String name) {
        return itemService.getFavoritesByName(userid, name);
    }

    @PostMapping(value = "/is-favorite")
    public Boolean isFavoriteItem(@RequestBody FavoriteRequest favoriteRequest) {
        return itemService.isFavoriteItem(favoriteRequest);
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
