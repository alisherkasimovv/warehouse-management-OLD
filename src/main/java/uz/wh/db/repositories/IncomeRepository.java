package uz.wh.db.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.wh.db.entities.documentation.Income;

import java.time.LocalDateTime;
import java.util.List;

public interface IncomeRepository extends JpaRepository<Income,Integer> {

    List<Income> findAll();
    Income findDistinctFirstByVendorIdAndDeletedFalseOrderByIdDesc(int vendorId);
    Income findById(int id);
    Income findByDocumentNo(String documentNo);
    Income findByVendorId(int i);
    Income findByOrderDate(LocalDateTime date);

}
