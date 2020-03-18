package uz.wh.db.repositories.documents;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.wh.db.entities.documents.items.OutgoItem;

public interface OutgoItemRepository extends JpaRepository<OutgoItem, Integer> {
}
