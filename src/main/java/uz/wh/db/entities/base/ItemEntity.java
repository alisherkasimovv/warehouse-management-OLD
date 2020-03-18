package uz.wh.db.entities.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class ItemEntity extends BaseEntity {

    @Nullable
    @Column(name = "product_id")
    private int productId;

    @Nullable
    @Column(name = "document_id")
    private int documentId;

    @Nullable
    @Column(name = "warehouse_id")
    private int warehouseId;

    @Nullable
    @Column(name = "quantity")
    private double quantity;

}
