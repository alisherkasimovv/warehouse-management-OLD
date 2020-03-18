package uz.wh.db.entities.documents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import uz.wh.db.entities.base.BaseEntity;
import uz.wh.db.enums.PaymentMethod;
import uz.wh.db.enums.PaymentType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "document_payments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Payment extends BaseEntity {

    @Nullable
    @Column(name = "document_number")
    private String documentNo;

    @Nullable
    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "payer_id")
    private int payerId;

    @Nullable
    @Column(name = "amount")
    private double amount;

    @Nullable
    @Column(name = "payment_type")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Nullable
    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

}
