package com.example.ShoppingWebsiteServer.service;

import com.example.ShoppingWebsiteServer.model.FavoriteRequest;
import com.example.ShoppingWebsiteServer.model.Item;
import com.example.ShoppingWebsiteServer.model.CustomUser;
import com.example.ShoppingWebsiteServer.repository.ItemRepository;
import com.example.ShoppingWebsiteServer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService implements ItemServiceInterface {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Item addToFavorites(String username, Integer itemId) {
        if (itemId == null) {
            System.out.println("You cannot add an item to favorites without item id");
            return null;
        }
        if (username == null) {
            System.out.println("You cannot add an item to favorites without username");
            return null;
        }
        Item item = itemRepository.getItemById(itemId);
        if (item == null) {
            System.out.println("The item with this id does not exist in the system");
            return null;
        }
        CustomUser user = userRepository.getUserByUsername(username);
        if (user == null) {
            System.out.println("The user with this username does not exist in the system");
            return null;
        }
        FavoriteRequest favoriteRequest = new FavoriteRequest(itemId, user.getId());
        if (isFavoriteItem(favoriteRequest)) {
            System.out.println("The item is already in the favorites");
            return null;
        }
        return itemRepository.addToFavorites(favoriteRequest, item);
    }

    @Override
    public String removeFromFavorites(String username, Integer itemId) {
        if (itemId == null) {
            return "You cannot remove an item from favorites without item id";
        }
        if (username == null) {
            return "You cannot remove an item from favorites without username";
        }
        CustomUser user = userRepository.getUserByUsername(username);
        if (user == null) {
            return "The user with this username does not exist in the system";
        }
        FavoriteRequest favoriteRequest = new FavoriteRequest(itemId, user.getId());
        if (!isFavoriteItem(favoriteRequest)) {
            return "The item is not in favorites and therefore cannot be removed from there";
        }
        return itemRepository.removeFromFavorites(favoriteRequest);
    }

    @Override
    public List<Item> getFavorites(String username) {
        if (username == null) {
            System.out.println("You cannot get favorite items without username");
            return null;
        }
        CustomUser user = userRepository.getUserByUsername(username);
        if (user == null) {
            System.out.println("The user with this id does not exist in the system");
            return null;
        }
        return itemRepository.getFavorites(user.getId());
    }

    @Override
    public List<Item> getFavoritesByName(String username, String name) {
        List<Item> favorites = getFavorites(username);
        if (favorites == null || favorites.size() == 0) {
            System.out.println("This user has no favorite items");
            return null;
        }
        if (name == null || name.trim().isEmpty()) {
            System.out.println("name is null");
            return null;
        }
        return itemRepository.getFavoritesByName(favorites, name);
    }

    @Override
    public Boolean isFavoriteItem(FavoriteRequest favoriteRequest) {
        return itemRepository.isFavoriteItem(favoriteRequest);
    }

    @Override
    public Item getItemById(Integer id) {
        if (id == null) {
            System.out.println("It is not possible to accept the item without id");
            return null;
        }
        return itemRepository.getItemById(id);
    }

    @Override
    public List<Item> getItemsByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("name is null");
            return null;
        }
        return itemRepository.getItemsByName(name);
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.getAllItems();
    }

    @Override
    public List<Item> getAllFavorites() {
        return itemRepository.getAllFavorites();
    }
}
