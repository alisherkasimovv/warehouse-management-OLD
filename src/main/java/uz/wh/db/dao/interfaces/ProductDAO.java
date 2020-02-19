package uz.wh.db.dao.interfaces;

import uz.wh.db.entities.Product;

import java.util.List;

public interface ProductDAO {

    void saveProduct(Product product);
    Product getById(int id);
    List<Product> getAll();
    String deleteById(int id);

}
