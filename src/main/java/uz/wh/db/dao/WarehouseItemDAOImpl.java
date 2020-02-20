package uz.wh.db.dao;

import org.springframework.stereotype.Service;
import uz.wh.collections.WarehouseStatus;
import uz.wh.db.dao.interfaces.WarehouseItemDAO;
import uz.wh.db.entities.Warehouse;
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
    public List<WarehouseItem> getItemsForWarehouse(int warehouseId) {
        return repository.findAllByWarehouseIdAndDeletedFalse(warehouseId);
    }

    @Override
    public List<WarehouseItem> getAllItemsForWarehouse(int warehouseId) {
        return null;
    }

    @Override
    public WarehouseItem getItemForWarehouse(int warehouseId, int productId) {
        return repository.findByWarehouseIdAndProductIdAndDeletedFalse(warehouseId, productId);
    }

    @Override
    public void registerIncomeToWarehouse(Item item, int warehouseId) {
        WarehouseItem wItem = repository.findByWarehouseIdAndProductIdAndDeletedFalse(warehouseId, item.getId());

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

    @Override
    public void registerOutgoFromWarehouse(Item item, int warehouseId) {
        WarehouseItem warehouseItem = repository.findByWarehouseIdAndProductIdAndDeletedFalse(warehouseId, item.getProductId());
    }
}
