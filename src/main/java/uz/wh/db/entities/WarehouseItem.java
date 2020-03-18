package uz.wh.db.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
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

    @Nullable
    @Column(name = "warehouse_id")
    private int warehouseId;

    @Nullable
    @Column(name = "product_id")
    private int productId;

    @Nullable
    @Column(name = "vendor_id")
    private int vendorId;

    @Nullable
    @Column(name = "quantity")
    private double quantity;

    @Nullable
    @Column(name = "price")
    private double price;

    @Nullable
    @Column(name = "cost")
    private double cost;

    @Nullable
    @Column(name = "price_total")
    private double priceTotal;

    @Nullable
    @Column(name = "cost_total")
    private double costTotal;

}
