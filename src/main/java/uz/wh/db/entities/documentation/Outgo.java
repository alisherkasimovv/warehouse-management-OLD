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
@Table(name = "dacument_outgo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Outgo extends DocumentEntity {

    @Column
    private String documentNo;

    @Column
    private int customerId;

    @Nullable
    @Column(name = "price")
    private double price;


    @Column
    private double balance;

}
