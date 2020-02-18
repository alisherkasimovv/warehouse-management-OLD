package uz.wh.db.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    @Id
    private int id;
    @Column
    private String name;
    @Column
    private String phone;
    @Column
    private String addres;

}
