package uz.wh.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.dao.interfaces.IncomeDAO;
import uz.wh.db.entities.Vendor;
import uz.wh.db.entities.documentation.Income;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/incomes")
public class IncomeController {
    private IncomeDAO incomeDAO ;
    public IncomeController(IncomeDAO incomeDAO) {
        this.incomeDAO=incomeDAO;
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<Income>> getAll(){
        return new ResponseEntity<>(incomeDAO.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{documentNo}", method = RequestMethod.GET)
    public ResponseEntity<Income> getOneOutgo(@PathVariable String documentNo) {
        return new ResponseEntity<>(incomeDAO.getByDocumentNo(documentNo), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{vendorid}", method = RequestMethod.GET)
    public ResponseEntity<Income> getByVendorName(@PathVariable int id) {
        return new ResponseEntity<>(incomeDAO.getByVendorId(id), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<ObjectAndMessage> saveIncome(@Valid @RequestBody Income income) {
        ObjectAndMessage objectAndMessage = incomeDAO.save(income);
        return new ResponseEntity<>(objectAndMessage, HttpStatus.OK);
    }

}
