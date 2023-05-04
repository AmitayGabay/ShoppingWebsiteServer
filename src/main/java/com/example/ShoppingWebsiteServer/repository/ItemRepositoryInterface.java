package com.example.ShoppingWebsiteServer.repository;

import com.example.ShoppingWebsiteServer.model.FavoriteRequest;
import com.example.ShoppingWebsiteServer.model.Item;

import java.util.List;

public interface ItemRepositoryInterface {
    Item addToFavorites(FavoriteRequest favoriteRequest, Item item);

    String removeFromFavorites(FavoriteRequest favoriteRequest);

    List<Item> getFavorites(Integer id);

    List<Item> getFavoritesByName(List<Item> favorites, String name);

    Boolean isFavoriteItem(FavoriteRequest favoriteRequest);

    Item getItemById(Integer id);

    List<Item> getItemsByName(String name);

    List<Item> getAllItems();

    List<Item> getAllFavorites();
}
