package uz.wh.db.dto;


import java.util.ArrayList;
import java.util.List;

public class ProductWithWarehouseQtyDTO {

    private int productId;
    private String productName;
    private String description;
    private String measurement;
    private String front;
    private List<WarehouseCountDTO> warehouses;

    public ProductWithWarehouseQtyDTO() {
        this.warehouses = new ArrayList<>();
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public List<WarehouseCountDTO> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(WarehouseCountDTO warehouse) {
        this.warehouses.add(warehouse);
    }
}
