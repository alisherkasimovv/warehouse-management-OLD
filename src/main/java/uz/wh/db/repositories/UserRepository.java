package uz.wh.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.wh.db.entities.User;
import uz.wh.db.enums.UserType;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByDeletedFalse();

    List<User> findAllByDeletedTrue();

    User findById(int id);

    User findByUsername(String username);

    User findByUserType(UserType userType);

    User findByFirstName(String firstName);

    User findByLastName(String lastName);

    User findByAddress(String address);

}
