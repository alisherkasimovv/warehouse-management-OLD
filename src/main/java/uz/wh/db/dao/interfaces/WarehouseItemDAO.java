package uz.wh.db.dao.interfaces;

import uz.wh.db.entities.WarehouseItem;
import uz.wh.db.entities.documentation.Item;

import java.util.List;

public interface WarehouseItemDAO {

    List<WarehouseItem> getAllDeletedItems();
    List<WarehouseItem> getAllItemsForWarehouse(int warehouseId);
    WarehouseItem getItemForWarehouse(int warehouseId, int productId);
    void registerIncomeToWarehouse(Item item, int warehouseId);
    void registerOutgoFromWarehouse(Item item, int warehouseId);


}
