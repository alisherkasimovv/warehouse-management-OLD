package uz.wh.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.wh.collections.UserAndMessage;
import uz.wh.collections.UserStats;
import uz.wh.db.dao.DataCollector;
import uz.wh.db.dao.interfaces.UserDAO;
import uz.wh.db.entities.User;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin
public class UserController {
    private UserDAO userDAO;
    private DataCollector collector;

    public UserController(UserDAO userDAO, DataCollector collector) {
        this.userDAO = userDAO;
        this.collector = collector;
    }

    @GetMapping("/get")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userDAO.getAllByDeletedFalse(), HttpStatus.OK);
    }

    @GetMapping("/get/deleted")
    public ResponseEntity<List<User>> getAllTrueUsers() {
        return new ResponseEntity<>(userDAO.getAllByDeletedTrue(), HttpStatus.OK);
    }

    @GetMapping("/get/statistics/{id}")
    public ResponseEntity<UserStats> getStatisticsForUser(@PathVariable int id) {
        return new ResponseEntity<>(collector.collectStatsForUser(id), HttpStatus.OK);
    }

    @GetMapping("/get/id={id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        return new ResponseEntity<>(userDAO.getById(id), HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable int id) {
        return new ResponseEntity<>(userDAO.deleteById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<UserAndMessage> saveAndEditUser(@Valid @RequestBody User user) {
        System.out.println(user.toString());
        System.out.println("We reached method");
        return new ResponseEntity<>(userDAO.saveAndEditUser(user), HttpStatus.OK);
    }
}
