package uz.wh.db.repositories.documents;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.wh.db.entities.documents.ReturnProduct;

import java.util.List;

public interface ReturnProductRepository extends JpaRepository<ReturnProduct,Integer> {

    List<ReturnProduct> findAll();
    ReturnProduct findDistinctFirstByCustomerIdAndDeletedFalseOrderByIdDesc(int customerId);
    ReturnProduct findByDocumentNo(String documentNo);
    ReturnProduct findById(int id);
    ReturnProduct findByCustomerId(int id);

}
