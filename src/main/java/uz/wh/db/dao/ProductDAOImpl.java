package uz.wh.db.dao;

import org.springframework.stereotype.Service;
import uz.wh.db.dao.interfaces.ProductDAO;
import uz.wh.db.entities.Product;
import uz.wh.db.entities.User;
import uz.wh.db.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductDAOImpl implements ProductDAO {

    private ProductRepository productRepository;

    public ProductDAOImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product getById(int id) {
    return productRepository.findById(id);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public String deleteById(int id) {
        Product product = productRepository.findById(id);
        product.setDeleted(true);
        productRepository.save(product);
        return "User successfully deleted!";
    }

}
