package uz.wh.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.wh.collections.ObjectAndMessage;
import uz.wh.collections.ItemOnWarehouse;
import uz.wh.db.dao.interfaces.WarehouseDAO;
import uz.wh.db.entities.Warehouse;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/warehouse")
@CrossOrigin
public class WarehouseController {

    private WarehouseDAO warehouseDAO;

    public WarehouseController(WarehouseDAO warehouseDAO) {
        this.warehouseDAO = warehouseDAO;
    }

    @GetMapping(value = "/get")
    @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_DIRECTOR', 'ROLE_STOREKEEPER')")
    public ResponseEntity<List<Warehouse>> getAll() {
        return new ResponseEntity<>(warehouseDAO.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_DIRECTOR', 'ROLE_STOREKEEPER')")
    public ResponseEntity<Warehouse> getById(@PathVariable int id) {
        return new ResponseEntity<>(warehouseDAO.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_DIRECTOR', 'ROLE_STOREKEEPER')")
    public ResponseEntity<ObjectAndMessage> saveWarehouse(@Valid @RequestBody Warehouse warehouse) {
        return new ResponseEntity<>(warehouseDAO.saveWarehouse(warehouse), HttpStatus.OK);
    }

    @GetMapping(value = "/get/count/warehouse={warehouseId}")
    @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_DIRECTOR', 'ROLE_STOREKEEPER')")
    public ResponseEntity<List<ItemOnWarehouse>> getCountWarehouseId(@PathVariable int warehouseId){
        return new ResponseEntity<>(warehouseDAO.countAllProductsOnOneWarehouse(warehouseId),HttpStatus.OK);
    }

    @GetMapping(value = "/get/count/product={id}")
    @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_DIRECTOR', 'ROLE_STOREKEEPER')")
    public ResponseEntity<List<ItemOnWarehouse>> getCountProductId(@PathVariable int id){
        return new ResponseEntity<>(warehouseDAO.countOneProductOnAllWarehouses(id),HttpStatus.OK);
    }

    @GetMapping(value = "/get/count/full")
    @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_DIRECTOR', 'ROLE_STOREKEEPER')")
    public ResponseEntity<List<ItemOnWarehouse>> getCountFull(){
        return new ResponseEntity<>(warehouseDAO.countProductsOnAllWarehouses(),HttpStatus.OK);
    }

    @GetMapping(value = "/get/count/warehouse={wId}&product={pId}")
    @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_DIRECTOR', 'ROLE_STOREKEEPER')")
    public ResponseEntity<ItemOnWarehouse> getCountOneWarehouseAndOneProduct(@PathVariable int wId, @PathVariable int pId){
        return new ResponseEntity<>(warehouseDAO.countOneProductOnOneWarehouse(pId, wId),HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_DIRECTOR')")
    public ResponseEntity<String> deleteWarehouse(@PathVariable int id) {
        return new ResponseEntity<>(warehouseDAO.deleteWarehouse(id), HttpStatus.OK);
    }

}
