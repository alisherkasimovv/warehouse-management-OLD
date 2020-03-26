package uz.wh.db.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import uz.wh.collections.UserAndMessage;
import uz.wh.db.dao.interfaces.UserDAO;
import uz.wh.db.entities.Role;
import uz.wh.db.entities.User;
import uz.wh.db.repositories.UserRepository;
import uz.wh.security.RoleAssignmentManager;

import java.util.List;

@Slf4j
@Service
public class UserDAOImpl implements UserDAO {

    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleAssignmentManager roleManager;

    public UserDAOImpl(UserRepository repository, BCryptPasswordEncoder passwordEncoder, RoleAssignmentManager roleManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.roleManager = roleManager;
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
        List<Role> roles = roleManager.assignAs("ROLE_USER");
        User temp = repository.findById(user.getId());

        if (temp != null) {
            temp = user;
            user.setRoles(roles);
            saved = repository.save(temp);
            message.setMessage("Foydalanuvchi yangilandi.");
        } else {
            user.setRoles(roles);
            saved = repository.save(user);
            message.setMessage("Yangi, " + saved.getFirstName() + " ismli fodalanuvchi ilovaga qo'shildi");
        }

        message.setUser(saved);
        return message;
    }

    @Override
    public User register(User user) {
        List<Role> roles = roleManager.assignAs("ROLE_USER");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roles);
        user.setDeleted(false);

        return repository.save(user);
    }

    @Override
    public User assignRolesForUserById(int userId, String assigningRole) {
        List<Role> roles = roleManager.assignAs(assigningRole);
        User user = repository.findById(userId);

        try {
            user.setRoles(roles);
        } catch (NullPointerException e) {
            log.warn("User with id -> {} not found.", userId);
        }

        return repository.save(user);
    }


}
