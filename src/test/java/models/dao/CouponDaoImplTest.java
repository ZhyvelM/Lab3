package models.dao;

import models.dao.interfaces.CouponDao;
import models.dao.interfaces.OrderDao;
import models.dao.interfaces.ProductDao;
import models.dao.interfaces.UserDao;
import models.entities.Coupon;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

class CouponDaoImplTest {
    UserDao userDao;
    OrderDao orderDao;
    ProductDao productDao;
    CouponDao couponDao;
    User user;
    Order order;
    Product product;
    Coupon coupon;

    @org.junit.jupiter.api.BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {userDao = new UserDaoImpl();
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

        couponDao = new CouponDaoImpl();
        coupon = new Coupon(
                0,
                25,
                "01.01.2021");
        couponDao.insert(order.getId(), coupon);

        user.add(order);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        userDao.delete(user);
        couponDao.delete(coupon);
        orderDao.delete(order);
    }

    @Test
    void findById() {
        Coupon new_coupon = couponDao.findById(coupon.getId());
        assertReflectionEquals(new_coupon, coupon);
    }

    @Test
    void findAll() {
        List<Coupon> new_coupons = couponDao.findAll();
        List<Coupon> coupons = new ArrayList<>();
        coupons.add(coupon);
        assertReflectionEquals(new_coupons, coupons);
    }

    @Test
    void insert() {
        Coupon new_coupon = couponDao.findById(coupon.getId());
        assertReflectionEquals(new_coupon, coupon);
    }

    @Test
    void update() {
        coupon.setDiscount(50);
        couponDao.update(order.getId(), coupon);
        Coupon new_coupon = couponDao.findById(coupon.getId());
        assertReflectionEquals(new_coupon, coupon);
    }

    @Test
    void delete() {
        couponDao.delete(coupon);
        Coupon new_coupon = couponDao.findById(coupon.getId());
        assertNull(new_coupon);
    }
}