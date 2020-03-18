package uz.wh.db.repositories.documents;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.wh.db.entities.documents.items.ReturnProductItem;

public interface ReturnProductItemRepository extends JpaRepository<ReturnProductItem, Integer> {
}
