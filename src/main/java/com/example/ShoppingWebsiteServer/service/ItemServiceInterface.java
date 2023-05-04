package com.example.ShoppingWebsiteServer.service;

import com.example.ShoppingWebsiteServer.model.FavoriteRequest;
import com.example.ShoppingWebsiteServer.model.Item;

import java.util.List;

public interface ItemServiceInterface {
    Item addToFavorites(String username, Integer itemId);

    String removeFromFavorites(String username, Integer itemId);

    List<Item> getFavorites(String username);

    List<Item> getFavoritesByName(String username, String name);

    Boolean isFavoriteItem(FavoriteRequest favoriteRequest);

    Item getItemById(Integer id);

    List<Item> getItemsByName(String name);

    List<Item> getAllItems();

    List<Item> getAllFavorites();
}
