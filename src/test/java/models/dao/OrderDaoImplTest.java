package models.dao;

import models.dao.interfaces.OrderDao;
import models.dao.interfaces.ProductDao;
import models.dao.interfaces.UserDao;
import models.entities.Order;
import models.entities.Product;
import models.entities.users.Client;
import models.entities.users.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class OrderDaoImplTest {
    UserDao userDao;
    OrderDao orderDao;
    ProductDao productDao;
    User user;
    Order order;
    Product product;

    @BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {
        userDao = new UserDaoImpl();
        user = new Client(
                0,
                "Petya",
                "Yakimovich",
                "example@gmail.com",
                "12345678",
                "+375331234567",
                false);
        userDao.insert(user);

        product = new Product(
                0,
                "PetyaCompany",
                "tOburetka",
                1000,
                10000);

        orderDao = new OrderDaoImpl();
        order = new Order(
                0,
                10,
                product);
        product.setId(order.getId());
        orderDao.insert(user.getId(), order);

        productDao = new ProductDaoImpl();
        productDao.insert(order.getId(), product);

        user.add(order);
    }
    @AfterEach
    void tearDown() {
        userDao.delete(user);
        orderDao.delete(order);
    }
    @Test
    void findAll() {
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        List<Order> new_orders = orderDao.findAll();
        assertReflectionEquals(new_orders, orders);
    }
    @Test
    void findByUser() {
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        List<Order> new_orders = orderDao.findByUser(user);
        assertReflectionEquals(new_orders, orders);
    }
    @Test
    void findById() {
        Order new_order = orderDao.findById(order.getId());
        assertReflectionEquals(new_order, order);
    }
    @Test
    void insert() {
        Order new_order = orderDao.findById(order.getId());
        assertReflectionEquals(new_order, order);
    }
    @Test
    void update() {
        order.setCount(999);
        orderDao.update(order);
        Order new_order = orderDao.findById(order.getId());
        assertReflectionEquals(new_order, order);
    }
    @Test
    void delete() {
        orderDao.delete(order);
        Order new_order = orderDao.findById(order.getId());
        assertNull(new_order);
    }
}