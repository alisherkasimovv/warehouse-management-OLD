package uz.wh.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.dao.interfaces.CustomerDAO;
import uz.wh.db.entities.Customer;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerDAO customerDAO;

    public CustomerController(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @GetMapping(value = "/get")
    @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_DIRECTOR', 'ROLE_STOREKEEPER')")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(customerDAO.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/deleted")
    @PreAuthorize("hasRole('ROLE_SUPERUSER')")
    public ResponseEntity<List<Customer>> getAllDeletedCustomers() {
        return new ResponseEntity<>(customerDAO.getAllDeleted(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_DIRECTOR', 'ROLE_STOREKEEPER')")
    public ResponseEntity<Customer> get(@PathVariable int id) {
        return new ResponseEntity<>(customerDAO.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_DIRECTOR')")
    public ResponseEntity<ObjectAndMessage> save(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerDAO.saveCustomer(customer), HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_DIRECTOR')")
    public ResponseEntity<Customer> delete(@PathVariable int id) {
        return new ResponseEntity<>(customerDAO.getById(id), HttpStatus.OK);
    }

}
