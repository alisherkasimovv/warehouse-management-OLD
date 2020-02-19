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
@Table(name = "document_outgo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Outgo extends DocumentEntity {


    private String documentNo;


    private int customerId;

    @Nullable
    private double price;


    private double balance;

}
