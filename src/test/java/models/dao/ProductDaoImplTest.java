package models.dao;

import models.dao.interfaces.OrderDao;
import models.dao.interfaces.ProductDao;
import models.dao.interfaces.UserDao;
import models.entities.Order;
import models.entities.Product;
import models.entities.users.Client;
import models.entities.users.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

class ProductDaoImplTest {
    UserDao userDao;
    OrderDao orderDao;
    ProductDao productDao;
    User user;
    Order order;
    Product product;

    @org.junit.jupiter.api.BeforeEach
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

        orderDao = new OrderDaoImpl();
        order = new Order(
                0,
                10,
                product
        );
        orderDao.insert(user.getId(), order);

        productDao = new ProductDaoImpl();
        product = new Product(
                0,
                "PetyaCompany",
                "tOburetka",
                1000,
                10000);
        productDao.insert(order.getId(), product);

        user.add(order);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        userDao.delete(user);
        productDao.delete(product);
        orderDao.delete(order);
    }

    @org.junit.jupiter.api.Test
    void findById() {
        Product new_product = productDao.findById(product.getId());
        assertReflectionEquals(new_product, product);
    }

    @org.junit.jupiter.api.Test
    void findAll() {
        List<Product> new_products = productDao.findAll();
        List<Product> products = new ArrayList<>();
        products.add(product);
        assertReflectionEquals(new_products, products);
    }

    @org.junit.jupiter.api.Test
    void insert() {
        Product new_product = productDao.findById(product.getId());
        assertReflectionEquals(new_product, product);
    }

    @org.junit.jupiter.api.Test
    void update() {
        product.setName("New Name");
        productDao.update(order.getId(), product);
        Product new_product = productDao.findById(product.getId());
        assertReflectionEquals(new_product, product);
    }

    @org.junit.jupiter.api.Test
    void delete() {
        productDao.delete(product);
        Product new_product = productDao.findById(product.getId());
        assertNull(new_product);
    }
}