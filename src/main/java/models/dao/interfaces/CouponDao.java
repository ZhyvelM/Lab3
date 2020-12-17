package models.dao.interfaces;

import models.entities.Coupon;

import java.util.List;

public interface CouponDao {
    List<Coupon> findAll();
    Coupon findById(int id);
    boolean insert(int orderId, Coupon coupon);
    boolean update(int orderId, Coupon coupon);
    boolean delete(Coupon coupon);
}
