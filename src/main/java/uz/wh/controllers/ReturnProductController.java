package uz.wh.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.dao.interfaces.ReturnProductDAO;
import uz.wh.db.dto.documents_dto.ReturnWithItemsDTO;
import uz.wh.db.entities.documents.ReturnProduct;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/returnproducts")
public class ReturnProductController {
    private ReturnProductDAO dao;

    public ReturnProductController(ReturnProductDAO dao) {
        this.dao = dao;
    }

    @GetMapping(value = "/get")
    @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_DIRECTOR', 'ROLE_STOREKEEPER')")
    public ResponseEntity<List<ReturnProduct>> getAll(){
        return new ResponseEntity<>(dao.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{documentNo}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_DIRECTOR', 'ROLE_STOREKEEPER')")
    public ResponseEntity<ReturnProduct> getDocumentNo(@PathVariable String documentNo) {
        return new ResponseEntity<>(dao.getByDocumentNo(documentNo), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{customerid}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_DIRECTOR', 'ROLE_STOREKEEPER')")
    public ResponseEntity<ReturnProduct> getByVendorName(@PathVariable int customer) {
        return new ResponseEntity<>(dao.getByCustomerId(customer), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_DIRECTOR', 'ROLE_STOREKEEPER')")
    public ResponseEntity<ObjectAndMessage> saveIncome(@Valid @RequestBody ReturnWithItemsDTO dto) {
        ObjectAndMessage objectAndMessage = dao.save(dto);
        return new ResponseEntity<>(objectAndMessage, HttpStatus.OK);
    }
    @GetMapping (value = "/deleted/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_DIRECTOR')")
    public ResponseEntity<ObjectAndMessage> deleteIncome(@PathVariable  int id) {
        ObjectAndMessage objectAndMessage = dao.deleteById(id);
        return new ResponseEntity<>(objectAndMessage, HttpStatus.OK);
    }
}
