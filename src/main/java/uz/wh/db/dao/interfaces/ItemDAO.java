package uz.wh.db.dao.interfaces;

import uz.wh.db.entities.documentation.Item;

import java.util.List;

public interface ItemDAO {

    List<Item> getAllItems();
    List<Item> getAllDeletedItems();
    List<Item> getAllItemsForDocument(String documentId);
    void saveItemList(List<Item> items, String documentId);
    void saveOneItem(Item item, String documentId);
    void deleteItemList(List<Item> items);
    void deleteOneItem(Item item);

}
