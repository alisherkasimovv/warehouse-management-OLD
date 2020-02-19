package uz.wh.db.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.wh.db.entities.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

@Entity
@Table(name = "db_warehouse")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Warehouse extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "productId")
    private int productId;

    @Column(name = "vendorId")
    private int vendorId;

    @Column(name = "quantity")
    private double quantity;

    @Column(name = "price")
    private double price;

    @Column(name = "cost")
    private double cost;

}
