package uz.wh.db.repositories.documents;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.wh.db.entities.documents.Outgo;

import java.time.LocalDate;
import java.util.List;

public interface OutgoRepository extends JpaRepository<Outgo,Integer> {

    List<Outgo> findAll();
    Outgo findDistinctFirstByCustomerIdAndDeletedFalseOrderByIdDesc(int customerId);
    Outgo findById(int id);
    Outgo findByDocumentNo(String documentNo);
    Outgo findByDocumentDate(LocalDate date);
    Outgo findByCustomerId(int id);

}
