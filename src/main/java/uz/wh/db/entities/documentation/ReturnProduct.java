package uz.wh.db.entities.documentation;

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
@Table(name = "document_returnproducs")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReturnProduct extends DocumentEntity {

    @Column
    private String documentNo;

    @Column
    private int customerId;

    @Nullable
    @Column
    private double price;

    @Column
    private double amount;


}
