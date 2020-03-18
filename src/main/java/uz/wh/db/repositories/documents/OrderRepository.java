package uz.wh.db.repositories.documents;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.wh.db.entities.documents.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    List<Order> findAll();
    Order findDistinctFirstByCustomerIdAndDeletedFalseOrderByIdDesc(int customerId);
    Order findById(int id);
    Order findByDocumentNo(String documentNo);
    Order findByCustomerId(int id);
    Order findByDocumentDate(LocalDate date);
}
