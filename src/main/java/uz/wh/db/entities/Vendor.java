package uz.wh.db.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import uz.wh.db.entities.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "db_vendors")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Vendor extends BaseEntity {

    @Nullable
    @Column
    private String name;

    @Nullable
    @Column
    private String phone;

    @Nullable
    @Column
    private String address;

}
