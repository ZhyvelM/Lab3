package models.dao.interfaces;

import models.entities.Product;
import models.entities.users.User;

import java.util.List;

public interface ProductDao {
    List<Product> findAll();
    Product findById(int id);
    boolean insert(int orderId, Product product);
    boolean update(int orderId, Product product);
    boolean delete(Product product);
}
