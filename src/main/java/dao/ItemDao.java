package dao;

import entity.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemDao {

    //create
    void add(Item item) throws SQLException;

    //read
    List<Item> getAllItems() throws SQLException;
    Item getByTitle(String title) throws SQLException;

    //update
    void update(Item item) throws SQLException;

    //delete
    void remove(Item item) throws SQLException;
}
