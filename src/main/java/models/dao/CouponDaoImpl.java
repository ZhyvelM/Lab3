package models.dao;

import models.dao.interfaces.CouponDao;
import models.entities.Coupon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CouponDaoImpl implements CouponDao {
    private Connection connection;

    public CouponDaoImpl() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internet_shop?serverTimezone=UTC", "root", "q1w2e3AsD");
    }

    @Override
    public Coupon findById(int id) {
        String sql = "SELECT * FROM internet_shop.coupons WHERE id = ?";
        Coupon coupon = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, String.valueOf(id));
            ResultSet rs = ps.executeQuery();
            rs.next();
            coupon = new Coupon(
                    rs.getInt("id"),
                    rs.getInt("discount"),
                    rs.getString("expire_date"));
        } catch (SQLException e) {
            return null;
        }
        return coupon;
    }
    @Override
    public List<Coupon> findAll() {
        String sql = "SELECT * FROM internet_shop.coupons";
        List<Coupon> coupons = new ArrayList<Coupon>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Coupon coupon = new Coupon(
                        rs.getInt("id"),
                        rs.getInt("discount"),
                        rs.getString("expire_date"));
                coupons.add(coupon);
            }
        } catch (SQLException e) {
            return null;
        }
        return coupons;
    }
    @Override
    public boolean insert(int orderId, Coupon coupon) {
        String sql = "INSERT INTO internet_shop.coupons (discount, expire_date, order_id) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, coupon.getDiscount());
            ps.setString(2, coupon.getExpireDate());
            ps.setInt(3, orderId);
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                coupon.setId((int) generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    @Override
    public boolean update(int orderId, Coupon coupon) {
        String sql = "UPDATE internet_shop.coupons " +
                "SET discount = ?, expire_date = ?, order_id = ? " +
                "WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, coupon.getDiscount());
            ps.setString(2, coupon.getExpireDate());
            ps.setInt(3, orderId);
            ps.setInt(4, coupon.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    @Override
    public boolean delete(Coupon coupon) {
        String sql = "DELETE FROM internet_shop.coupons WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, String.valueOf(coupon.getId()));
            ps.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}