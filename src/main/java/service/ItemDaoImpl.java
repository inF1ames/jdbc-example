package service;

import bl.Util;
import dao.ItemDao;
import entity.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl extends Util implements ItemDao {

    private Connection connection;

    @Override
    public void add(Item item) throws SQLException {
        connection = getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO ITEM (ID, TITLE, DESCRIPTION, PRICE) VALUES(?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, item.getId());
            preparedStatement.setString(2, item.getTitle());
            preparedStatement.setString(3, item.getDescription());
            preparedStatement.setBigDecimal(4, item.getPrice());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();

            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public List<Item> getAllItems() throws SQLException {
        connection = getConnection();
        List<Item> itemList = new ArrayList<>();

        String sql = "SELECT ID, TITLE, DESCRIPTION, PRICE FROM ITEM";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getLong("ID"));
                item.setTitle(resultSet.getString("TITLE"));
                item.setDescription(resultSet.getString("DESCRIPTION"));
                item.setPrice(resultSet.getBigDecimal("PRICE"));

                itemList.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return itemList;
    }

    @Override
    public Item getByTitle(String title) throws SQLException {
        connection = getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "SELECT ID, TITLE, DESCRIPTION, PRICE FROM ITEM WHERE TITLE=?";

        Item item = new Item();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.first();
                 item.setId(resultSet.getLong("ID"));
                 item.setTitle(resultSet.getString("TITLE"));
                 item.setDescription(resultSet.getString("DESCRIPTION"));
                 item.setPrice(resultSet.getBigDecimal("PRICE"));

                 preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return item;
    }

    @Override
    public void update(Item item) throws SQLException {
        connection = getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE ITEM SET TITLE=?, DESCRIPTION=?, PRICE=? WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, item.getTitle());
            preparedStatement.setString(2, item.getDescription());
            preparedStatement.setBigDecimal(3, item.getPrice());
            preparedStatement.setLong(4, item.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void remove(Item item) throws SQLException {
        connection = getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM ITEM WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, item.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
