package models.dao.interfaces;

import models.entities.users.User;

public interface UserOrderDao {
    User findById(int id);
}
