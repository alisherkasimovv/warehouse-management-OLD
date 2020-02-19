package uz.wh.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.dao.interfaces.VendorDAO;
import uz.wh.db.entities.Vendor;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/vendors")
public class VendorController {
    private VendorDAO vendorDAO;

    public VendorController(VendorDAO vendorDAO) {
        this.vendorDAO = vendorDAO;
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<Vendor>> getAll() {
        return new ResponseEntity<>(vendorDAO.getAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<ObjectAndMessage> saveVendor(@Valid @RequestBody Vendor vendor) {
        ObjectAndMessage objectAndMessage = vendorDAO.saveEditVendor(vendor);
        return new ResponseEntity<>(objectAndMessage, HttpStatus.OK);
    }

    @GetMapping(value = "/delete")
    public ResponseEntity<ObjectAndMessage> deleteById(@PathVariable int id) {
        return new ResponseEntity<>(vendorDAO.deleteVendorById(id), HttpStatus.OK);
    }
}
