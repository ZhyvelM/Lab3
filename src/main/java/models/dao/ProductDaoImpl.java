package models.dao;

import models.dao.interfaces.ProductDao;
import models.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private Connection connection;

    public ProductDaoImpl() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internet_shop?serverTimezone=UTC", "root", "q1w2e3AsD");
    }

    @Override
    public Product findById(int id) {
        String sql = "SELECT * FROM internet_shop.products WHERE id = ?";
        Product product = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, String.valueOf(id));
            ResultSet rs = ps.executeQuery();
            rs.next();
            product = new Product(
                    rs.getInt("id"),
                    rs.getString("manufacturer"),
                    rs.getString("name"),
                    rs.getDouble("cost"),
                    rs.getInt("count"));
        } catch (SQLException e) {
            return null;
        }
        return product;
    }
    @Override
    public List<Product> findAll() {
        String sql = "SELECT * FROM internet_shop.products";
        List<Product> products = new ArrayList<Product>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("manufacturer"),
                        rs.getString("name"),
                        rs.getDouble("cost"),
                        rs.getInt("count"));
                products.add(product);
            }
        } catch (SQLException e) {
            return null;
        }
        return products;
    }
    @Override
    public boolean insert(int orderId, Product product) {
        String sql = "INSERT INTO internet_shop.products (manufacturer, name, cost, count, order_id) " +
                "VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getManufacturer());
            ps.setString(2, product.getName());
            ps.setDouble(3, product.getCost());
            ps.setInt(4, product.getCount());
            ps.setInt(5, orderId);
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setId((int) generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    @Override
    public boolean update(int orderId, Product product) {
        String sql = "UPDATE internet_shop.products " +
                "SET manufacturer = ?, name = ?, cost = ?, count = ?, order_id = ? " +
                "WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, product.getManufacturer());
            ps.setString(2, product.getName());
            ps.setDouble(3, product.getCost());
            ps.setInt(4, product.getCount());
            ps.setInt(5, orderId);
            ps.setInt(6, product.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    @Override
    public boolean delete(Product product) {
        String sql = "DELETE FROM internet_shop.products WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, String.valueOf(product.getId()));
            ps.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}