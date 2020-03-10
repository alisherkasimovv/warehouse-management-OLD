package uz.wh.db.dto;


import java.util.ArrayList;
import java.util.List;

public class ProductWithWarehouseQtyDTO {

    private int id;
    private String name;
    private String description;
    private String measurement;
    private double price;
    private double overallQuantity;
    private List<WarehouseCountDTO> warehouses;

    public ProductWithWarehouseQtyDTO() {
        this.overallQuantity = 0;
        this.warehouses = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOverallQuantity() {
        return overallQuantity;
    }

    public void setOverallQuantity(double overallQuantity) {
        this.overallQuantity += overallQuantity;
    }

    public List<WarehouseCountDTO> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(WarehouseCountDTO warehouse) {
        this.warehouses.add(warehouse);
    }
}
