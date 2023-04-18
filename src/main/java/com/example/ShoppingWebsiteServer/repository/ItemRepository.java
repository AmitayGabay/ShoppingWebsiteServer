package com.example.ShoppingWebsiteServer.repository;

import com.example.ShoppingWebsiteServer.model.FavoriteRequest;
import com.example.ShoppingWebsiteServer.model.Item;
import com.example.ShoppingWebsiteServer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemRepository implements ItemRepositoryInterface {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String ITEMS_TABLE = "items";
    private static final String ITEM_TO_USER_TABLE = "item_to_user";

    @Override
    public Item addToFavorites(FavoriteRequest favoriteRequest, Item item) {
        try {
            String sql = String.format("INSERT INTO %s (item_id, user_id) VALUES (?,?)", ITEM_TO_USER_TABLE);
            jdbcTemplate.update(sql, favoriteRequest.getItemId(), favoriteRequest.getUserId());
            return item;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public String removeFromFavorites(FavoriteRequest favoriteRequest) {
        try {
            String sql = String.format("DELETE FROM %s WHERE item_id = ? AND user_id = ?", ITEM_TO_USER_TABLE);
            jdbcTemplate.update(sql, favoriteRequest.getItemId(), favoriteRequest.getUserId());
            return "The item hes been successfully removed from favorites";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public List<Item> getFavoritesByUserId(Integer id) {
        try {
            String sql = String.format("SELECT items.id, items.title, items.picture, items.usd_price, items.amount FROM %s \n" +
                    "INNER JOIN %s \n" +
                    "ON items.id = item_to_user.item_id \n" +
                    "WHERE item_to_user.user_id = ?", ITEMS_TABLE, ITEM_TO_USER_TABLE);
            List<Item> favorites = jdbcTemplate.query(sql, new ItemMapper(), id);
            return favorites;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Item> getFavoritesByName(List<Item> favorites, String name) {
        List<Item> results = new ArrayList<Item>();
        favorites.forEach(favorite -> {
            if(favorite.getTitle().contains(name))
                results.add(favorite);
        });
        return results;
    }

    @Override
    public Boolean isFavoriteItem(FavoriteRequest favoriteRequest){
        try{
            String sql = String.format("SELECT items.id, items.title, items.picture, items.usd_price, items.amount FROM %s \n" +
                            "INNER JOIN %s \n" +
                            "ON items.id = item_to_user.item_id \n" +
                            "WHERE item_to_user.item_id = ? AND item_to_user.user_id = ?", ITEMS_TABLE, ITEM_TO_USER_TABLE);
            Item item = jdbcTemplate.queryForObject(sql, new ItemMapper(), favoriteRequest.getItemId(), favoriteRequest.getUserId());
            if(item == null){
                return false;
            }
                return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Item getItemById(Integer id) {
        try {
            String sql = String.format("SELECT * FROM %s WHERE id = ?", ITEMS_TABLE);
            Item item = jdbcTemplate.queryForObject(sql, new ItemMapper(), id);
            return item;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Item> getItemsByName(String name) {
        try {
            String sql = String.format("SELECT * FROM %s WHERE title LIKE ?", ITEMS_TABLE);
            String helper = "%" + name + "%";
            List<Item> items = jdbcTemplate.query(sql, new ItemMapper(), helper);
            return items;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Item> getAllItems() {
        try {
            String sql = String.format("SELECT * FROM %s", ITEMS_TABLE);
            List<Item> items = jdbcTemplate.query(sql, new ItemMapper());
            return items;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Item> getAllFavorites() {
        try{
            String sql = String.format("SELECT items.id, items.title, items.picture, items.usd_price, items.amount FROM %s \n" +
                    "INNER JOIN %s \n" +
                    "ON items.id = item_to_user.item_id", ITEMS_TABLE, ITEM_TO_USER_TABLE);
            List<Item> favorites = jdbcTemplate.query(sql, new ItemMapper());
            List<Integer> favoritesIds = new ArrayList<Integer>();
            List<Item> unrepeatedFavorites = new ArrayList<Item>();
            for (int i = 0; i < favorites.size(); i++) {
                if(!favoritesIds.contains(favorites.get(i).getId())){
                    favoritesIds.add(favorites.get(i).getId());
                    unrepeatedFavorites.add(favorites.get(i));
                }
            }
            return unrepeatedFavorites;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
