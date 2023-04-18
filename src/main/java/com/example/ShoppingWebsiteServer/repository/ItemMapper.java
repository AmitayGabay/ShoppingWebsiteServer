package com.example.ShoppingWebsiteServer.repository;

import com.example.ShoppingWebsiteServer.model.Item;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemMapper implements RowMapper<Item> {

    @Override
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Item(rs.getInt("id"),
                rs.getString("title"),
                rs.getString("picture"),
                rs.getDouble("usd_price"),
                rs.getInt("amount"));
    }
}
