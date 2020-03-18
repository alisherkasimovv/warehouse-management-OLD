package uz.wh.db.entities.documents.items;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import uz.wh.db.entities.base.ItemEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "document_return_items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReturnProductItem extends ItemEntity {

    @Nullable
    @Column(name = "price")
    private double price;

    @Nullable
    @Column(name = "price_total")
    private double priceTotal;

}
