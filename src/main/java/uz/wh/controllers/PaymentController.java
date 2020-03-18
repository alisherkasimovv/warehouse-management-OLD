package uz.wh.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.dao.interfaces.PaymentDAO;
import uz.wh.db.entities.documents.Payment;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/payments")
public class PaymentController {

    private PaymentDAO paymentDAO;

    public PaymentController(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<Payment>> getAllPayments() {
        return new ResponseEntity<>(paymentDAO.getAllAvailablePayments(), HttpStatus.OK);
    }

    @PostMapping(value = "/accept")
    public ResponseEntity<ObjectAndMessage> acceptPayment(@Valid @RequestBody Payment payment) {
        return new ResponseEntity<>(paymentDAO.acceptPayment(payment), HttpStatus.OK);
    }

}
