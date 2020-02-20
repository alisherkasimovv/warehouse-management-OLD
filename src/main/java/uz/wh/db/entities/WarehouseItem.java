package uz.wh.db.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.wh.db.entities.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "db_warehouse_items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WarehouseItem extends BaseEntity {

    @Column(name = "warehouse_id")
    private int warehouseId;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "vendor_id")
    private int vendorId;

    @Column(name = "quantity")
    private double quantity;

    @Column(name = "price")
    private double price;

    @Column(name = "cost")
    private double cost;

}
