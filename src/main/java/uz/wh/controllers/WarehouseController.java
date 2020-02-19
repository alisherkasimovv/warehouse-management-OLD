package uz.wh.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.wh.collections.WarehouseStatus;
import uz.wh.db.dao.interfaces.WarehouseDAO;
import uz.wh.db.entities.Warehouse;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    private WarehouseDAO warehouseDAO;

    public WarehouseController(WarehouseDAO warehouseDAO) {
        this.warehouseDAO = warehouseDAO;
    }

    @GetMapping(value = "/get")
    private ResponseEntity<List<Warehouse>> getAll() {
        return new ResponseEntity<>(warehouseDAO.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    private ResponseEntity<Warehouse> getById(@PathVariable int id) {
        return new ResponseEntity<>(warehouseDAO.getById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/get/count/warehouse={id}")
    private ResponseEntity<List<WarehouseStatus>> getCountWarehouseId(int id){
        return new ResponseEntity<>(warehouseDAO.countProductsOnOneWarehouse(id),HttpStatus.OK);
    }

    @GetMapping(value = "/get/count/product={id}")
    private ResponseEntity<WarehouseStatus> getCountProductId(int id){
        return new ResponseEntity<>(warehouseDAO.countOneProductOnAllWarehouses(id),HttpStatus.OK);
    }

    @GetMapping(value = "/get/count/full")
    private ResponseEntity<List<WarehouseStatus>> getCountFull(){
        return new ResponseEntity<>(warehouseDAO.countProductsOnAllWarehouses(),HttpStatus.OK);
    }

    @GetMapping(value = "/get/count/warehouse={wId}&product={pId}")
    private ResponseEntity<WarehouseStatus> getCountOneWarehouseAndOneProduct(int wid,int pid){
        return new ResponseEntity<>(warehouseDAO.countOneProductOnOneWarehouse(pid,wid),HttpStatus.OK);
    }

}
