package models.dao;

import models.dao.interfaces.OrderDao;
import models.dao.interfaces.UserDao;
import models.dao.interfaces.UserOrderDao;
import models.entities.Order;
import models.entities.Product;
import models.entities.users.Admin;
import models.entities.users.Client;
import models.entities.users.User;
import models.entities.users.UserType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserOrderDaoImpl implements UserOrderDao {
    private UserDao userDao;
    private OrderDao orderDao;

    public UserOrderDaoImpl() throws SQLException, ClassNotFoundException {
        userDao = new UserDaoImpl();
        orderDao = new OrderDaoImpl();
    }

    /*
    @Override
    public User findById(int id) {
        String sql = "SELECT * FROM internet_shop.users " +
                "JOIN orders ON orders.user_id = users.id " +
                "JOIN products ON orders.product_id = products.id " +
                "WHERE users.id = ?";
        User user = null;
        List<Order> orders = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            rs.next();
            UserType userType = UserType.valueOf(rs.getString("users.user_type"));
            String name = rs.getString("users.name");
            String surname = rs.getString("users.surname");
            String email = rs.getString("users.email");
            String password = rs.getString("users.password");
            String number = rs.getString("users.number");

            do {
                Product product = new Product(
                        rs.getInt("products.id"),
                        rs.getString("products.manufacturer"),
                        rs.getString("products.name"),
                        rs.getDouble("products.cost"),
                        rs.getInt("products.count"));
                Order order = new Order(
                        rs.getInt("orders.id"),
                        rs.getInt("orders.count"),
                        product);
                orders.add(order);
            }
            while (rs.next());

            switch (userType) {
                case Admin:
                    user = new Admin(id, name, surname, email, password, number, orders);
                    break;
                case Client:
                    user = new Client(id, name, surname, email, password, number, orders);
            }
        } catch (SQLException e) {
            return null;
        }
        return user;
    }
    */

    @Override
    public User findById(int id) {
        User user = userDao.findById(id);
        user.setOrders(orderDao.findByUser(user));
        return user;
    }
}
