package uz.wh.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.wh.db.entities.documentation.ReturnProduct;

import java.util.List;

public interface ReturnProductRepository extends JpaRepository<ReturnProduct,Integer> {

    List<ReturnProduct> findAll();

    ReturnProduct findByDocumentNo(String documentNo);

    ReturnProduct findById(int id);

    ReturnProduct findByCustomerId(int id);

}
