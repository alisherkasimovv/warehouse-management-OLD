package uz.wh.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.dao.interfaces.IncomeDAO;
import uz.wh.db.dao.interfaces.OrderDAO;
import uz.wh.db.entities.documentation.Income;
import uz.wh.db.entities.documentation.Order;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrderController {
    private OrderDAO orderDAO;
    public OrderController(OrderDAO order) {
        this.orderDAO =order;
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<Order>> getAll(){
        return new ResponseEntity<>(orderDAO.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{documentNo}", method = RequestMethod.GET)
    public ResponseEntity<Order> getDocumentNo(@PathVariable String documentNo) {
        return new ResponseEntity<>(orderDAO.getByDocumentNo(documentNo), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{customerid}", method = RequestMethod.GET)
    public ResponseEntity<Order> getByCustomerName(@PathVariable int customerid) {
        return new ResponseEntity<>(orderDAO.getByCustomerId(customerid), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<ObjectAndMessage> saveOrder(@Valid @RequestBody Order order) {
        ObjectAndMessage objectAndMessage = orderDAO.save(order);
        return new ResponseEntity<>(objectAndMessage, HttpStatus.OK);
    }
    @GetMapping(value = "/deleted/{id}")
    public ResponseEntity<ObjectAndMessage> deleteOrder(@PathVariable int id) {
        ObjectAndMessage objectAndMessage = orderDAO.deleteById(id);
        return new ResponseEntity<>(objectAndMessage, HttpStatus.OK);
    }

}
