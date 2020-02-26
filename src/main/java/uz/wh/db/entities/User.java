package uz.wh.db.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import uz.wh.db.entities.base.BaseEntity;
import uz.wh.db.enums.UserTypes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

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

    @Column(name = "db_firstname")
    private String firstName;

    @Column(name = "db_lastname")
    private String lastName;

    @Column(name = "db_phonenumber")
    private String phone;

    @Column(name = "db_address")
    private String address;

    @Nullable
    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private UserTypes userTypes;

}
//    @ManyToMany
//    private List<Role> roles;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return roles;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
