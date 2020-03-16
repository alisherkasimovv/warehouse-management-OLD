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
@Table(name = "document_incomes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Income extends DocumentEntity {

    @Column(name = "vendor_id")
    private int vendorId;

    @Column(name = "cost")
    private double cost;

    // Field shows balance of vendor.
    // Will be calculated from distraction payment from cost.
    @Column(name = "balance")
    private double balance;

}

