package uz.wh.db.dao;

import org.springframework.stereotype.Service;
import uz.wh.collections.UserAndMessage;
import uz.wh.db.dao.interfaces.UserDAO;
import uz.wh.db.entities.User;
import uz.wh.db.enums.UserType;
import uz.wh.db.repositories.UserRepository;

import java.util.List;

@Service
public class UserDAOImpl implements UserDAO {
    private UserRepository repository;

    public UserDAOImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> getAllByDeletedFalse() {
        return repository.findAllByDeletedFalse();
    }

    @Override
    public List<User> getAllByDeletedTrue() {
        return repository.findAllByDeletedTrue();
    }

    @Override
    public List<User> getAllUsersByTheirType(UserType userType) {
        return repository.findAllByUserTypeAndDeletedFalse(userType);
    }

    @Override
    public User getById(int id) {
        return repository.findById(id);
    }

    @Override
    public User getByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public String deleteById(int id) {
        User user = repository.findById(id);
        user.setDeleted(true);
        repository.save(user);
        return "User successfully deleted!";
    }

    @Override
    public UserAndMessage saveAndEditUser(User user) {
        User saved;
        UserAndMessage message = new UserAndMessage();
        User temp = repository.findById(user.getId());
        if (temp != null) {
            temp = user;
            saved = repository.save(temp);
            message.setMessage("Foydalanuvchi yangilandi.");

        } else {
            saved = repository.save(user);
            message.setMessage("Yangi, " + saved.getFirstName() + " ismli fodalanuvchi ilovaga qo'shildi");
        }
        message.setUser(saved);
        return message;
    }
}
