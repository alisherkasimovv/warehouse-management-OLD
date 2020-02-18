package uz.wh.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.wh.collections.VendorAndMessage;
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
    public ResponseEntity<VendorAndMessage> saveVendor(@Valid @RequestBody Vendor vendor) {
        VendorAndMessage vendorAndMessage = vendorDAO.saveEditVendor(vendor);
        return new ResponseEntity<>(vendorAndMessage, HttpStatus.OK);
    }

    @GetMapping(value = "/delete")
    public ResponseEntity<List<Vendor>> deleteById(@PathVariable int id){
        vendorDAO.deleteVendorById(id);
        return new ResponseEntity<>(vendorDAO.getAll(),HttpStatus.OK);
    }
}
