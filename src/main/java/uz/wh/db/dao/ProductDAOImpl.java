package uz.wh.db.dao;

import org.springframework.stereotype.Service;
import uz.wh.collections.ObjectAndMessage;
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
    public ObjectAndMessage saveProduct(Product product) {
        Product saved;
        ObjectAndMessage oam = new ObjectAndMessage();

        Product temp = productRepository.findById(product.getId());
        if (temp != null) {
            temp = product;
            saved = productRepository.save(temp);
            oam.setMessage("Mahsulot haqidagi ma'lumot yangilandi");
        } else {
            saved = productRepository.save(product);
            oam.setMessage("Yangi mahsulot yaratildi");
        }
        oam.setObject(saved);
        return oam;
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
