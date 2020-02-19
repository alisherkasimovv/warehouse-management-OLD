package uz.wh.db.entities.base;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class DocumentEntity extends BaseEntity {

    @Column(name = "reference_number", unique = true)
    private String reference;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column
    private boolean status;
}
