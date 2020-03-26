package uz.wh.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.dao.interfaces.OutgoDAO;
import uz.wh.db.dto.documents_dto.OutgoWithItemsDTO;
import uz.wh.db.entities.documents.Outgo;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ougoes")
@CrossOrigin
public class OutgoController {
    private OutgoDAO outgoDAO;
    public OutgoController(OutgoDAO outgoDAO) {
        this.outgoDAO =outgoDAO;
    }

    @GetMapping(value = "/get")
    @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_DIRECTOR', 'ROLE_STOREKEEPER')")
    public ResponseEntity<List<Outgo>> getAll(){
        return new ResponseEntity<>(outgoDAO.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/document={documentNo}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_DIRECTOR', 'ROLE_STOREKEEPER')")
    public ResponseEntity<Outgo> getDocumentNo(@PathVariable String documentNo) {
        return new ResponseEntity<>(outgoDAO.getByDocumentNo(documentNo), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/customer={customerId}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_DIRECTOR', 'ROLE_STOREKEEPER')")
    public ResponseEntity<Outgo> getByCustomerName(@PathVariable int customerId) {
        return new ResponseEntity<>(outgoDAO.getByCustomerId(customerId), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_DIRECTOR', 'ROLE_STOREKEEPER')")
    public ResponseEntity<ObjectAndMessage> saveIncome(@Valid @RequestBody OutgoWithItemsDTO outgo) {
        ObjectAndMessage objectAndMessage = outgoDAO.save(outgo);
        return new ResponseEntity<>(objectAndMessage, HttpStatus.OK);
    }
    @PostMapping(value = "/deleted/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_DIRECTOR')")
    public ResponseEntity<ObjectAndMessage> deleteIncome(@PathVariable int id) {
        ObjectAndMessage objectAndMessage = outgoDAO.deleteById(id);
        return new ResponseEntity<>(objectAndMessage, HttpStatus.OK);
    }
}
