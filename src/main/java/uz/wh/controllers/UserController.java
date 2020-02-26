package uz.wh.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.wh.collections.UserAndMessage;
import uz.wh.db.dao.interfaces.UserDAO;
import uz.wh.db.entities.User;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin
public class UserController {
    UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/get")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userDAO.getAllByDeletedFalse(), HttpStatus.OK);
    }

    @GetMapping("/get/deleted")
    public ResponseEntity<List<User>> getAllTrueUsers() {
        return new ResponseEntity<>(userDAO.getAllByDeletedTrue(), HttpStatus.OK);
    }

    @GetMapping("/get/id={id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        return new ResponseEntity<>(userDAO.getById(id), HttpStatus.OK);
    }

    @GetMapping("/get/first={firstName}")
    public ResponseEntity<User> getUserByFirstName(@PathVariable String firstName) {
        return new ResponseEntity<>(userDAO.getByFirstName(firstName), HttpStatus.OK);
    }

    @GetMapping("/get/last={lastName}")
    public ResponseEntity<User> getUserByLastName(@PathVariable String lastName) {
        return new ResponseEntity<>(userDAO.getByLastName(lastName), HttpStatus.OK);
    }

    @GetMapping("/get/username={username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return new ResponseEntity<>(userDAO.getByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/get/address={address}")
    public ResponseEntity<User> getUserByAddress(@PathVariable String address) {
        return new ResponseEntity<>(userDAO.getByAddress(address), HttpStatus.OK);
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
