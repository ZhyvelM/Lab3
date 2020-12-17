package models.dao;

import models.dao.interfaces.UserDao;
import models.entities.users.Admin;
import models.entities.users.Client;
import models.entities.users.User;
import models.entities.users.UserType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private Connection connection;

    public UserDaoImpl() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internet_shop?serverTimezone=UTC", "root", "q1w2e3AsD");
    }

    @Override
    public List<User> findByType(UserType userType) {
        String sql = "SELECT * FROM internet_shop.users WHERE user_type = ?";
        List<User> users = new ArrayList<User>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, String.valueOf(userType));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = null;
                switch (userType) {
                    case Admin:
                        user = new Admin(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("surname"),
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getString("number"),
                                rs.getBoolean("banned"));
                        break;
                    case Client:
                        user = new Client(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("surname"),
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getString("number"),
                                rs.getBoolean("banned"));
                }
                users.add(user);
            }
        } catch (SQLException e) {
            return null;
        }
        return users;
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM internet_shop.users";
        List<User> users = new ArrayList<User>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserType userType = UserType.valueOf(rs.getString("user_type"));
                User user = null;
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String number = rs.getString("number");
                boolean banned = rs.getBoolean("banned");
                switch (userType) {
                    case Admin:
                        user = new Admin(id, name, surname, email, password, number, banned);
                        break;
                    case Client:
                        user = new Client(id, name, surname, email, password, number, banned);
                }
                users.add(user);
            }
        } catch (SQLException e) {
            return null;
        }
        return users;
    }

    @Override
    public User findById(int id) {
        String sql = "SELECT * FROM internet_shop.users WHERE id = ?";
        User user = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, String.valueOf(id));
            ResultSet rs = ps.executeQuery();
            rs.next();
            UserType userType = UserType.valueOf(rs.getString("user_type"));
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String number = rs.getString("number");
            boolean banned = rs.getBoolean("banned");
            switch (userType) {
                case Admin:
                    user = new Admin(id, name, surname, email, password, number, banned);
                    break;
                case Client:
                    user = new Client(id, name, surname, email, password, number, banned);
            }
        } catch (SQLException e) {
            return null;
        }
        return user;
    }

    @Override
    public boolean insert(User user) {
        String sql = "INSERT INTO internet_shop.users (name, surname, email, password, number, user_type, banned) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getNumber());
            ps.setString(6, user.getUserType().toString());
            ps.setBoolean(7, user.isBanned());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId((int) generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean update(User user) {
        String sql = "UPDATE internet_shop.users " +
                "SET name = ?, surname = ?, email = ?, password = ?, number = ?, user_type = ?, banned = ? " +
                "WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getNumber());
            ps.setString(6, user.getUserType().toString());
            ps.setBoolean(7, user.isBanned());
            ps.setInt(8, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(User user) {
        String sql = "DELETE FROM internet_shop.users WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, String.valueOf(user.getId()));
            ps.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}