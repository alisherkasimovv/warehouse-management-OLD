package uz.wh.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.wh.db.dao.interfaces.ProductDAO;
import uz.wh.db.entities.Product;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController  {

    private ProductDAO productDAO;

    public ProductController(ProductDAO productDAO) {this.productDAO = productDAO;}

    @GetMapping(value = "/get")
    public ResponseEntity<List<Product>> getAll(){return new ResponseEntity<>(productDAO.getAll(), HttpStatus.OK);}

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Product> getById(@PathVariable int id){return new ResponseEntity<>(productDAO.getById(id),HttpStatus.OK);}

    @PostMapping(value = "/save")
    public ResponseEntity<List<Product>> save(@Valid @RequestBody Product product){
    productDAO.saveProduct(product);
    return new ResponseEntity<>(productDAO.getAll(),HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        return new ResponseEntity<>(productDAO.deleteById(id),HttpStatus.OK);
    }
}
