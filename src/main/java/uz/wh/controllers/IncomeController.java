package uz.wh.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.dao.interfaces.IncomeDAO;
import uz.wh.db.dto.IncomeWithItemsDTO;
import uz.wh.db.entities.Vendor;
import uz.wh.db.entities.documentation.Income;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/incomes")
@CrossOrigin
public class IncomeController {
    private int reference;
    private IncomeDAO incomeDAO;

    public IncomeController(IncomeDAO incomeDAO) {
        this.incomeDAO=incomeDAO;
        this.reference = 0;
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<Income>> getAll(){
        return new ResponseEntity<>(incomeDAO.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{documentNo}", method = RequestMethod.GET)
    public ResponseEntity<Income> getDocumentNo(@PathVariable String documentNo) {
        return new ResponseEntity<>(incomeDAO.getByDocumentNo(documentNo), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{vendorid}", method = RequestMethod.GET)
    public ResponseEntity<Income> getByVendorName(@PathVariable int vendorid) {
        return new ResponseEntity<>(incomeDAO.getByVendorId(vendorid), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<ObjectAndMessage> saveIncome(@Valid @RequestBody IncomeWithItemsDTO income) {
        ObjectAndMessage objectAndMessage = incomeDAO.save(income);
        return new ResponseEntity<>(objectAndMessage, HttpStatus.OK);
    }
    @GetMapping (value = "/deleted/{id}")
    public ResponseEntity<ObjectAndMessage> deleteIncome(@PathVariable  int id) {
        ObjectAndMessage objectAndMessage = incomeDAO.deleteById(id);
        return new ResponseEntity<>(objectAndMessage, HttpStatus.OK);
    }

}
