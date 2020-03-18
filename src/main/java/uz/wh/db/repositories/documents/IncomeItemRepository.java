package uz.wh.db.repositories.documents;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.wh.db.entities.documents.items.IncomeItem;

public interface IncomeItemRepository extends JpaRepository<IncomeItem, Integer> {
}
