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
    public Item addToFavorites(FavoriteRequest favoriteRequest) {
        if (favoriteRequest.getItemId() == null) {
            System.out.println("You cannot add an item to favorites without item id");
            return null;
        }
        if (favoriteRequest.getUserId() == null) {
            System.out.println("You cannot add an item to favorites without user id");
            return null;
        }
        Item item = itemRepository.getItemById(favoriteRequest.getItemId());
        if (item == null) {
            System.out.println("The item with this id does not exist in the system");
            return null;
        }
        CustomUser user = userRepository.getUserById(favoriteRequest.getUserId());
        if (user == null) {
            System.out.println("The user with this id does not exist in the system");
            return null;
        }
        if (isFavoriteItem(favoriteRequest)) {
            System.out.println("The item is already in the favorites");
            return null;
        }
        return itemRepository.addToFavorites(favoriteRequest, item);
    }

    @Override
    public String removeFromFavorites(FavoriteRequest favoriteRequest) {
        if (favoriteRequest.getItemId() == null) {
            return "You cannot remove an item from favorites without item id";
        }
        if (favoriteRequest.getUserId() == null) {
            return "You cannot remove an item from favorites without user id";
        }
        if (!isFavoriteItem(favoriteRequest)) {
            return "The item is not in favorites and therefore cannot be removed from there";
        }
        return itemRepository.removeFromFavorites(favoriteRequest);
    }

    @Override
    public List<Item> getFavoritesByUserId(Integer id) {
        if (id == null) {
            System.out.println("You cannot get favorite items without user id");
            return null;
        }
        CustomUser user = userRepository.getUserById(id);
        if (user == null) {
            System.out.println("The user with this id does not exist in the system");
            return null;
        }
        return itemRepository.getFavoritesByUserId(id);
    }

    @Override
    public List<Item> getFavoritesByName(Integer userid, String name) {
        List<Item> favorites = getFavoritesByUserId(userid);
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
