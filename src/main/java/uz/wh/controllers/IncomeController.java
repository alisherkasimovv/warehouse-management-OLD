package uz.wh.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.dao.interfaces.IncomeDAO;
import uz.wh.db.dto.documents_dto.IncomeWithItemsDTO;
import uz.wh.db.entities.documents.Income;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/incomes")
@CrossOrigin
public class IncomeController {
    private IncomeDAO incomeDAO;

    public IncomeController(IncomeDAO incomeDAO) {
        this.incomeDAO=incomeDAO;
    }

    @GetMapping(value = "/get")
    @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_DIRECTOR', 'ROLE_STOREKEEPER')")
    public ResponseEntity<List<Income>> getAll(){
        return new ResponseEntity<>(incomeDAO.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/document={documentNo}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_DIRECTOR', 'ROLE_STOREKEEPER')")
    public ResponseEntity<Income> getDocumentNo(@PathVariable String documentNo) {
        return new ResponseEntity<>(incomeDAO.getByDocumentNo(documentNo), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/id={vendorId}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_DIRECTOR', 'ROLE_STOREKEEPER')")
    public ResponseEntity<Income> getByVendorName(@PathVariable int vendorId) {
        return new ResponseEntity<>(incomeDAO.getByVendorId(vendorId), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_DIRECTOR', 'ROLE_STOREKEEPER')")
    public ResponseEntity<ObjectAndMessage> saveIncome(@Valid @RequestBody IncomeWithItemsDTO income) {
        ObjectAndMessage objectAndMessage = incomeDAO.save(income);
        return new ResponseEntity<>(objectAndMessage, HttpStatus.OK);
    }
    @GetMapping (value = "/deleted/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_DIRECTOR')")
    public ResponseEntity<ObjectAndMessage> deleteIncome(@PathVariable  int id) {
        ObjectAndMessage objectAndMessage = incomeDAO.deleteById(id);
        return new ResponseEntity<>(objectAndMessage, HttpStatus.OK);
    }

}
