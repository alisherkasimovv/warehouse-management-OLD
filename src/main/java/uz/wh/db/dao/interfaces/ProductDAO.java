package uz.wh.db.dao.interfaces;

import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.entities.Product;

import java.util.List;

public interface ProductDAO {

    ObjectAndMessage saveProduct(Product product);
    Product getById(int id);
    List<Product> getAll();
    String deleteById(int id);

}
