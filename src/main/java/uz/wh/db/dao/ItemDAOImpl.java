package uz.wh.db.dao;

import org.springframework.stereotype.Service;
import uz.wh.db.dao.interfaces.ItemDAO;
import uz.wh.db.entities.documentation.Item;
import uz.wh.db.repositories.ItemRepository;

import java.util.List;

@Service
public class ItemDAOImpl implements ItemDAO {

    private ItemRepository repository;

    public ItemDAOImpl(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Item> getAllItems() {
        return repository.findAllByDeletedFalse();
    }

    @Override
    public List<Item> getAllDeletedItems() {
        return repository.findAllByDeletedTrue();
    }

    @Override
    public List<Item> getAllItemsForDocument(String documentId) {
        return repository.findAllByDocumentId(documentId);
    }

    @Override
    public void saveItemList(List<Item> items, String documentId) {
        for (Item item : items) {
            item.setDocumentId(documentId);
        }
        repository.saveAll(items);

    }

    @Override
    public void saveOneItem(Item item, String documentId) {
        item.setDocumentId(documentId);
        repository.save(item);
    }

    @Override
    public void deleteItemList(List<Item> items) {
        repository.deleteAll(items);
    }

    @Override
    public void deleteOneItem(Item item) {
        repository.delete(item);
    }
}
