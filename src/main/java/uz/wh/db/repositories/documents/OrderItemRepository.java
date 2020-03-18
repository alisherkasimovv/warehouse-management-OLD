package uz.wh.db.repositories.documents;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.wh.db.entities.documents.items.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
