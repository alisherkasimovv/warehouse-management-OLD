package uz.wh.db.entities.base;
import lombok.Getter;
import lombok.Setter;
import uz.wh.db.enums.DocumentType;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
public abstract class DocumentEntity extends BaseEntity {

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "document_number")
    private String documentNo;

    @Column(name = "document_status")
    private boolean status;

    @Column(name = "document_type")
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

}
