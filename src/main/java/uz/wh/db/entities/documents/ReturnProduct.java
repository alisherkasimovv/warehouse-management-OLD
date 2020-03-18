package uz.wh.db.entities.documents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import uz.wh.db.entities.base.DocumentEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "document_return_products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReturnProduct extends DocumentEntity {

    @Column(name = "customer_id")
    private int customerId;

    @Nullable
    @Column(name = "total_quantity")
    private double totalQuantity;

}
