package uz.wh.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.wh.db.dao.DataCollector;
import uz.wh.db.dto.ProductWithWarehouseQtyDTO;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/collector")
public class DataCollectorController {

    private DataCollector collector;

    public DataCollectorController(DataCollector collector) {
        this.collector = collector;
    }

    @GetMapping(value = "/product-counts")
    public ResponseEntity<List<ProductWithWarehouseQtyDTO>> collectAllProducts() {
        return new ResponseEntity<>(
                collector.collectAllProductsAndTheirCounts(), HttpStatus.OK);
    }

}
