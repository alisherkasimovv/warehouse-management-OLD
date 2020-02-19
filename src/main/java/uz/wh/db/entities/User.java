package uz.wh.db.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.wh.db.entities.base.BaseEntity;
import uz.wh.db.enums.UserType;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "db_users")
public class User extends BaseEntity {

    @Column(name = "db_username", unique = true)
    private String username;

    @Column(name = "db_password", nullable = false)
    private String password;

    @Column(name = "db_firstname", nullable = false)
    private String firstName;

    @Column(name = "db_lastname")
    private String lastName;

    @Column(name = "db_phonenumber")
    private String phone;

    @Column(name = "db_address", nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "db_usertype", nullable = false)
    private UserType userType;
}
