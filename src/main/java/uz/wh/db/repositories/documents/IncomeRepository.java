package uz.wh.db.repositories.documents;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.wh.db.entities.documents.Income;

import java.time.LocalDate;
import java.util.List;

public interface IncomeRepository extends JpaRepository<Income,Integer> {

    List<Income> findAll();
    Income findDistinctFirstByVendorIdAndDeletedFalseOrderByIdDesc(int vendorId);
    Income findById(int id);
    Income findByDocumentNo(String documentNo);
    Income findByVendorId(int i);
    Income findByDocumentDate(LocalDate date);

}
