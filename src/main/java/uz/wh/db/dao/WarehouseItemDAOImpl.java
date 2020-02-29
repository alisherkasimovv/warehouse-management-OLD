package uz.wh.db.dao;

import org.springframework.stereotype.Service;
import uz.wh.db.dao.interfaces.WarehouseItemDAO;
import uz.wh.db.entities.WarehouseItem;
import uz.wh.db.entities.documentation.Item;
import uz.wh.db.repositories.WarehouseItemRepository;

import java.util.List;

@Service
public class WarehouseItemDAOImpl implements WarehouseItemDAO {

    private WarehouseItemRepository repository;

    public WarehouseItemDAOImpl(WarehouseItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<WarehouseItem> getAllDeletedItems() {
        return repository.findAllByDeletedTrue();
    }

    @Override
    public List<WarehouseItem> getAllItemsForWarehouse(int warehouseId) {
        return repository.findAllByWarehouseIdAndDeletedFalse(warehouseId);
    }

    @Override
    public WarehouseItem getItemForWarehouse(int warehouseId, int productId) {
        return repository.findByWarehouseIdAndProductIdAndDeletedFalse(warehouseId, productId);
    }

    /**
     * Method registers income of the item to chosen warehouse.
     *
     * @param item Registering item.
     * @param warehouseId Chosen warehouse id.
     */
    @Override
    public void registerIncomeToWarehouse(Item item, int warehouseId) {
        WarehouseItem wItem =
                repository.findByWarehouseIdAndProductIdAndDeletedFalse(warehouseId, item.getId());

        if (wItem != null) {
            wItem.setQuantity(wItem.getQuantity() + item.getQuantity());
        } else {
            wItem = new WarehouseItem();
            wItem.setProductId(item.getProductId());
            wItem.setWarehouseId(warehouseId);
            wItem.setQuantity(item.getQuantity());
            wItem.setCost(item.getCost());
            wItem.setPrice(item.getPrice());
        }
        repository.save(wItem);

    }

    /**
     * Method registers outgo of item from chosen warehouse.
     *
     * @param item Registering item object.
     * @param warehouseId Chosen warehouse id.
     */
    @Override
    public void registerOutgoFromWarehouse(Item item, int warehouseId) {
        WarehouseItem wItem =
                repository.findByWarehouseIdAndProductIdAndDeletedFalse(warehouseId, item.getProductId());

        if (wItem != null) {
            if (wItem.getQuantity() < item.getQuantity()) return;

            wItem.setQuantity(wItem.getQuantity() - item.getQuantity());

            // if quantity of the item in warehouse will become equal to 0
            // this item will be deleted from warehouse
            if (wItem.getQuantity() == 0) {
                repository.delete(wItem);
            }
        } else {
            return;
        }
        repository.save(wItem);

    }

    @Override
    public double countAllItemsByWarehouse(int warehouseId) {
        return repository.countAllByWarehouseId(warehouseId);
    }
}
