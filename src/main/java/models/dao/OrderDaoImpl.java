package models.dao;

import models.dao.interfaces.OrderDao;
import models.entities.Order;
import models.entities.Product;
import models.entities.users.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private Connection connection;

    public OrderDaoImpl() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internet_shop?serverTimezone=UTC", "root", "q1w2e3AsD");
    }

    @Override
    public List<Order> findAll() {
        String sql = "SELECT orders.id, orders.count, products.id, products.manufacturer, products.name, products.cost, products.count\n" +
            "FROM orders\n" +
            "JOIN products ON orders.id = products.order_id\n";
        List<Order> orders = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {Order order;
                int id = rs.getInt("orders.id");
                int count = rs.getInt("orders.count");
                try {
                    Product product = new Product(
                            rs.getInt("products.id"),
                            rs.getString("products.manufacturer"),
                            rs.getString("products.name"),
                            rs.getDouble("products.cost"),
                            rs.getInt("products.count"));
                    order = new Order(id, count, product);
                } catch(Exception ex) {
                    order = new Order(id, count);
                }
                orders.add(order);
            }
        } catch (SQLException e) {
            return null;
        }
        return orders;
    }
    @Override
    public List<Order> findByUser(User user) {
        String sql = "SELECT orders.id, orders.count, products.id, products.manufacturer, products.name, products.cost, products.count\n" +
                "FROM orders\n" +
                "JOIN products ON orders.id = products.order_id\n" +
                "WHERE orders.user_id = ? " +
                "GROUP BY orders.user_id";
        List<Order> orders = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order;
                int id = rs.getInt("orders.id");
                int count = rs.getInt("orders.count");
                try {
                    Product product = new Product(
                            rs.getInt("products.id"),
                            rs.getString("products.manufacturer"),
                            rs.getString("products.name"),
                            rs.getDouble("products.cost"),
                            rs.getInt("products.count"));
                    order = new Order(id, count, product);
                } catch(Exception ex) {
                    order = new Order(id, count);
                }
                orders.add(order);
            }
        } catch (SQLException e) {
            return null;
        }
        return orders;
    }
    @Override
    public Order findById(int id) {
        String sql = "SELECT orders.id, orders.count, products.id, products.manufacturer, products.name, products.cost, products.count\n" +
                "FROM orders\n" +
                "JOIN products ON orders.id = products.order_id\n" +
                "WHERE orders.id = ?";
        Order order;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt("orders.count");
            Product product = new Product(
                    rs.getInt("products.id"),
                    rs.getString("products.manufacturer"),
                    rs.getString("products.name"),
                    rs.getDouble("products.cost"),
                    rs.getInt("products.count"));
            order = new Order(id, count, product);
        } catch (SQLException e) {
            return null;
        }
        return order;
    }
    @Override
    public boolean insert(int user_id, Order order) {
        String sql = "INSERT INTO internet_shop.orders (count, user_id) VALUES (?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getCount());
            ps.setInt(2, user_id);
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                order.setId((int) generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    @Override
    public boolean update(Order order) {
        String sql = "UPDATE internet_shop.orders " +
                "SET count = ? " +
                "WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, order.getCount());
            ps.setInt(2, order.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    @Override
    public boolean delete(Order order) {
        String sql = "DELETE FROM internet_shop.orders WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, String.valueOf(order.getId()));
            ps.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}
