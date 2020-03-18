package uz.wh.db.entities.base;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;
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

    @Column(name = "transaction_date")
    private LocalDate documentDate;

    @Column(name = "document_number")
    private String documentNo;

    @Column(name = "document_type")
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    @Column(name = "is_document_closed")
    private boolean closed;

    @Nullable
    @Column(name = "total_price")
    private double totalPrice;

    @Nullable
    @Column(name = "total_cost")
    private double totalCost;

    @Nullable
    @Column(name = "balance")
    private double balance;

}
