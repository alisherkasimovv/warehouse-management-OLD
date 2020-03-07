package uz.wh.db.dao;

import org.springframework.stereotype.Service;
import uz.wh.db.dao.interfaces.ItemDAO;
import uz.wh.db.dao.interfaces.WarehouseItemDAO;
import uz.wh.db.entities.documentation.Item;
import uz.wh.db.enums.DocumentType;
import uz.wh.db.repositories.ItemRepository;

import java.util.List;

@Service
public class ItemDAOImpl implements ItemDAO {

    private ItemRepository repository;
    private WarehouseItemDAO warehouseItemDAO;

    public ItemDAOImpl(ItemRepository repository, WarehouseItemDAO warehouseItemDAO) {
        this.repository = repository;
        this.warehouseItemDAO = warehouseItemDAO;
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

    /**
     * Saving document items and registering them on warehouse according to document type.
     * Income and Return documents will increase the count of items, but Outgo document
     * will decrease count of items from particular warehouse.
     *
     * @param items List of registered items.
     * @param documentId Receiving pre-generated document id.
     * @param type Document type according which items will be registered in warehouse.
     * @param warehouseId Chosen warehouse id.
     */
    @Override
    public void saveItemList(List<Item> items, String documentId, DocumentType type, int warehouseId) {
        for (Item item : items) {
            item.setDocumentId(documentId);

            switch (type) {
                case INCOME:
                case RETURN:
                    warehouseItemDAO.registerIncomeToWarehouse(item, warehouseId);
                    break;
                case OUTGO:
                    warehouseItemDAO.registerOutgoFromWarehouse(item, warehouseId);
                    break;
                case ORDER:
                    break;
            }

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
