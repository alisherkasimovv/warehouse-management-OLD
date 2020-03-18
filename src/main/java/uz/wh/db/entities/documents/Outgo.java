package uz.wh.db.entities.documents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.wh.db.entities.base.DocumentEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "document_outgo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Outgo extends DocumentEntity {

    @Column(name = "customer_id")
    private int customerId;

}
