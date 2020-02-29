package uz.wh.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.wh.db.entities.documentation.Outgo;

import java.time.LocalDateTime;
import java.util.List;

public interface OutgoRepository extends JpaRepository<Outgo,Integer> {

    List<Outgo> findAll();
    Outgo findDistinctFirstByCustomerIdAndDeletedFalseOrderByIdDesc(int customerId);
    Outgo findById(int id);
    Outgo findByDocumentNo(String documentNo);
    Outgo findByOrderDate(LocalDateTime date);
    Outgo findByCustomerId(int id);

}
