package uz.wh.db.entities.documentation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import uz.wh.db.entities.base.BaseEntity;
import uz.wh.db.entities.base.DocumentEntity;

import javax.persistence.*;

@Entity
@Table(name = "document_items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Item extends BaseEntity {

    @Nullable
    @Column(name = "product_id")
    private int productId;

    @Nullable
    @Column(name = "document_id")
    private int documentId;

    @Nullable
    @Column(name = "cost")
    private double cost;

    @Nullable
    @Column(name = "price")
    private double price;

    @Nullable
    @Column(name = "quantity")
    private double quantity;

}
