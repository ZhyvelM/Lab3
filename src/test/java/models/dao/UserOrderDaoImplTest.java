package models.dao;

import models.dao.interfaces.OrderDao;
import models.dao.interfaces.ProductDao;
import models.dao.interfaces.UserDao;
import models.dao.interfaces.UserOrderDao;
import models.entities.Order;
import models.entities.Product;
import models.entities.users.Client;
import models.entities.users.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

class UserOrderDaoImplTest {
    UserDao userDao;
    OrderDao orderDao;
    ProductDao productDao;
    UserOrderDao userOrderDao;
    User user;
    Order order;
    Product product;

    @BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {
        userOrderDao = new UserOrderDaoImpl();

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
        productDao.delete(product);
        orderDao.delete(order);
    }

    @Test
    void findById() {
        User new_user = userOrderDao.findById(user.getId());
        assertReflectionEquals(new_user, user);
    }
}