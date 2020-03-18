package uz.wh.db.dao.interfaces;

import uz.wh.db.entities.WarehouseItem;
import uz.wh.db.entities.documents.items.IncomeItem;
import uz.wh.db.entities.documents.items.OutgoItem;
import uz.wh.db.entities.documents.items.ReturnProductItem;

import java.util.List;

public interface WarehouseItemDAO {

    List<WarehouseItem> getAllDeletedItems();
    List<WarehouseItem> getAllItemsForWarehouse(int warehouseId);
    WarehouseItem getItemForWarehouse(int warehouseId, int productId);
    void registerIncomeToWarehouse(IncomeItem item);
    void registerOutgoFromWarehouse(OutgoItem item);
    void registerReturnToWarehouse(ReturnProductItem item);
    double countAllItemsByWarehouse(int warehouseId);

}
