package uz.wh.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.wh.db.entities.documentation.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findAll();

    Order findById(int id);

    Order findByDocumentNo(String documentNo);

    Order findByCustomerId(int id);

    Order findByDate(LocalDateTime date);
}
