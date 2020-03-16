package uz.wh.db.dao.interfaces;

import uz.wh.db.entities.documentation.Item;
import uz.wh.db.enums.DocumentType;

import java.util.List;

public interface ItemDAO {

    List<Item> getAllItems();
    List<Item> getAllDeletedItems();
    List<Item> getAllItemsForDocument(String documentId);
    void saveItemList(List<Item> items, int documentId, DocumentType type, int warehouseId);
    void saveOneItem(Item item, int documentId);
    void deleteItemList(List<Item> items);
    void deleteOneItem(Item item);

}
