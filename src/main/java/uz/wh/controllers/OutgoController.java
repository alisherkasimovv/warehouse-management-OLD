package uz.wh.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.dao.interfaces.IncomeDAO;
import uz.wh.db.dao.interfaces.OutgoDAO;
import uz.wh.db.entities.documentation.Income;
import uz.wh.db.entities.documentation.Outgo;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ougoes")
public class OutgoController {
    private OutgoDAO outgoDAO;
    public OutgoController(OutgoDAO outgoDAO) {
        this.outgoDAO =outgoDAO;
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<Outgo>> getAll(){
        return new ResponseEntity<>(outgoDAO.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{documentNo}", method = RequestMethod.GET)
    public ResponseEntity<Outgo> getDocumentNo(@PathVariable String documentNo) {
        return new ResponseEntity<>(outgoDAO.getByDocumentNo(documentNo), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{customerid}", method = RequestMethod.GET)
    public ResponseEntity<Outgo> getByCutomerName(@PathVariable int id) {
        return new ResponseEntity<>(outgoDAO.getByCustomerId(id), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<ObjectAndMessage> saveIncome(@Valid @RequestBody Outgo outgo) {
        ObjectAndMessage objectAndMessage = outgoDAO.save(outgo);
        return new ResponseEntity<>(objectAndMessage, HttpStatus.OK);
    }
    @PostMapping(value = "/deleted")
    public ResponseEntity<ObjectAndMessage> deleteIncome(@Valid @RequestBody int id) {
        ObjectAndMessage objectAndMessage = outgoDAO.deleteById(id);
        return new ResponseEntity<>(objectAndMessage, HttpStatus.OK);
    }
}
