package uz.wh.db.dao.interfaces;

import uz.wh.collections.UserAndMessage;
import uz.wh.db.entities.User;

import java.util.List;

public interface UserDAO {

    List<User> getAllByDeletedFalse();
    List<User> getAllByDeletedTrue();
    User getById(int id);
    String deleteById(int id);
    UserAndMessage saveAndEditUser(User user);

    User register(User user);
    User assignRolesForUserById(int userId, String assigningRole);
    User getByUsername(String username);

}