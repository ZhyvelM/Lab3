package models.dao.interfaces;

import models.entities.Order;
import models.entities.users.User;

import java.util.List;

public interface OrderDao {
    List<Order> findAll();
    List<Order> findByUser(User user);
    Order findById(int id);
    boolean insert(int user_id, Order order);
    boolean update(Order order);
    boolean delete(Order order);
}
