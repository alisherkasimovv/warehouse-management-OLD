package uz.wh.db.dao.interfaces;

import uz.wh.collections.UserAndMessage;
import uz.wh.db.entities.User;
import uz.wh.db.enums.UserType;

import java.util.List;

public interface UserDAO {

    List<User> getAllByDeletedFalse();

    List<User> getAllByDeletedTrue();
    List<User> getAllUsersByTheirType(UserType userType);

    User getById(int id);

    User getByUsername(String username);

    String deleteById(int id);

    UserAndMessage saveAndEditUser(User user);


}
