package uz.wh.db.dao.interfaces;

import uz.wh.collections.ObjectAndMessage;
import uz.wh.collections.ItemOnWarehouse;
import uz.wh.db.entities.Warehouse;

import java.util.List;

public interface WarehouseDAO  {

    Warehouse getById(int id);
    List<Warehouse> getAll();
    ObjectAndMessage saveWarehouse(Warehouse warehouse);
    String deleteWarehouse(int id);
    List<ItemOnWarehouse> countProductsOnAllWarehouses();
    List<ItemOnWarehouse> countOneProductOnAllWarehouses(int productId);
    List<ItemOnWarehouse> countAllProductsOnOneWarehouse(int warehouseId);
    ItemOnWarehouse countOneProductOnOneWarehouse(int productId, int warehouseId);

}
