package uz.wh.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.wh.db.entities.documentation.Item;
import uz.wh.db.enums.DocumentType;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findAllByDeletedFalse();
    List<Item> findAllByDeletedTrue();
    List<Item> findAllByDocumentIdAndDocumentType(int id, DocumentType type);
    Item findByDocumentIdAndDocumentType(int id, DocumentType type);

}
