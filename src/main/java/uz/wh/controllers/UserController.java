package uz.wh.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.wh.collections.UserAndMessage;
import uz.wh.db.dao.interfaces.UserDAO;
import uz.wh.db.entities.User;
import uz.wh.db.enums.UserType;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userDAO.getAllByDeletedFalse(), HttpStatus.OK);
    }

    @GetMapping("/getAllTrue")
    public ResponseEntity<List<User>> getAllTrueUsers() {
        return new ResponseEntity<>(userDAO.getAllByDeletedTrue(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        return new ResponseEntity<>(userDAO.getById(id), HttpStatus.OK);
    }

    @GetMapping("/getName")
    public ResponseEntity<User> getUserByFirstName(@PathVariable String firstName) {
        return new ResponseEntity<>(userDAO.getByFirstName(firstName), HttpStatus.OK);
    }

    @GetMapping("/getLastName")
    public ResponseEntity<User> getUserByLastName(@PathVariable String lastName) {
        return new ResponseEntity<>(userDAO.getByLastName(lastName), HttpStatus.OK);
    }

    @GetMapping("/getUsername")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return new ResponseEntity<>(userDAO.getByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/getType")
    public ResponseEntity<User> getUserByUserType(@PathVariable UserType userType) {
        return new ResponseEntity<>(userDAO.getByUserType(userType), HttpStatus.OK);
    }

    @GetMapping("/getAddress")
    public ResponseEntity<User> getUserByAddress(@PathVariable String address) {
        return new ResponseEntity<>(userDAO.getByAddress(address), HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable int id) {
        return new ResponseEntity<>(userDAO.deleteById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<UserAndMessage> saveAndEditUser(@RequestBody User user) {
        return new ResponseEntity<>(userDAO.saveAndEditUser(user), HttpStatus.OK);
    }
}
