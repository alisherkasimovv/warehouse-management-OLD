package uz.wh.db.entities.documentation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.wh.db.entities.base.DocumentEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "document_orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order extends DocumentEntity {

    @Column
    private int customerId;

    @Column
    private double balance;
}
