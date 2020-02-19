package uz.wh.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.wh.db.entities.documentation.Income;

import java.time.LocalDateTime;
import java.util.List;

public interface IncomeRepository extends JpaRepository<Income,Integer> {
    List<Income> findAll();

    Income findById(int id);

    Income findByDocumentNo(String documentNo);

    Income findByVendorId(int i);

    Income findByDate(LocalDateTime date);



}
