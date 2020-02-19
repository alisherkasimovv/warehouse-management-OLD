package uz.wh.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WarehouseStatus {

    @Column(name = "productId")
    private int productId;

    @Column(name = "productName")
    private String productName;

    @Column(name = "warehouseId")
    private int warehouseId;

    @Column(name = "warehouseName")
    private String warehouseName;

    @Column(name = "quantity")
    private double quantity;

    @Column(name = "totalCost")
    private double totalCost;

    @Column(name = "cost")
    private double cost;

    @Column(name = "totalPrice")
    private double totalPrice;

    @Column(name = "price")
    private double price;

}
