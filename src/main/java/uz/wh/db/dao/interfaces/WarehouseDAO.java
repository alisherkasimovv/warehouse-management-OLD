package uz.wh.db.dao.interfaces;

import uz.wh.collections.WarehouseStatus;
import uz.wh.db.entities.Warehouse;

import java.util.List;

public interface WarehouseDAO  {

    Warehouse getById(int id);
    List<Warehouse> getAll();
    List<WarehouseStatus> countProductsOnAllWarehouses();
    WarehouseStatus countOneProductOnAllWarehouses(int productId);
    List<WarehouseStatus> countProductsOnOneWarehouse(int warehouseId);
    WarehouseStatus countOneProductOnOneWarehouse(int productId, int warehouseId);

}
