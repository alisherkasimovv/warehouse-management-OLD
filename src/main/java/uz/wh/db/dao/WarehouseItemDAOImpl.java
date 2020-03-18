package uz.wh.db.dao;

import org.springframework.stereotype.Service;
import uz.wh.db.dao.interfaces.WarehouseItemDAO;
import uz.wh.db.entities.WarehouseItem;
import uz.wh.db.entities.documents.items.IncomeItem;
import uz.wh.db.entities.documents.items.OutgoItem;
import uz.wh.db.entities.documents.items.ReturnProductItem;
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
     * @param item Registering incoming item.
     */
    @Override
    public void registerIncomeToWarehouse(IncomeItem item) {
        WarehouseItem wItem =
                repository.findByWarehouseIdAndProductIdAndDeletedFalse(item.getWarehouseId(), item.getId());

        if (wItem != null) {
            wItem.setQuantity(wItem.getQuantity() + item.getQuantity());
        } else {
            wItem = new WarehouseItem();
            wItem.setProductId(item.getProductId());
            wItem.setWarehouseId(item.getWarehouseId());
            wItem.setQuantity(item.getQuantity());
            wItem.setCost(item.getCost());
        }
        repository.save(wItem);

    }

    /**
     * Method registers outgo of item from chosen warehouse.
     *
     * @param item Registering outgo item object.
     */
    @Override
    public void registerOutgoFromWarehouse(OutgoItem item) {
        WarehouseItem wItem =
                repository.findByWarehouseIdAndProductIdAndDeletedFalse(item.getWarehouseId(), item.getProductId());

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

    /**
     * Method adds returned product to warehouse.
     *
     * @param item Returned item.
     */
    @Override
    public void registerReturnToWarehouse(ReturnProductItem item) {
        WarehouseItem wItem =
                repository.findByWarehouseIdAndProductIdAndDeletedFalse(item.getWarehouseId(), item.getProductId());

        if (wItem != null) {
            wItem.setQuantity(wItem.getQuantity() + item.getQuantity());
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
