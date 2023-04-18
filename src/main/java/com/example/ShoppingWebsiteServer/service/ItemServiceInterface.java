package com.example.ShoppingWebsiteServer.service;

import com.example.ShoppingWebsiteServer.model.FavoriteRequest;
import com.example.ShoppingWebsiteServer.model.Item;

import java.util.List;

public interface ItemServiceInterface {
    Item addToFavorites(FavoriteRequest favoriteRequest);

    String removeFromFavorites(FavoriteRequest favoriteRequest);

    List<Item> getFavoritesByUserId(Integer id);

    List<Item> getFavoritesByName(Integer userid, String name);

    Boolean isFavoriteItem(FavoriteRequest favoriteRequest);

    Item getItemById(Integer id);

    List<Item> getItemsByName(String name);

    List<Item> getAllItems();

    List<Item> getAllFavorites();
}
