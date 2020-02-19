package uz.wh.db.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.wh.db.entities.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "db_users")
public class User extends BaseEntity implements UserDetails {

    @Column(name = "db_username", unique = true)
    private String username;

    @Column(name = "db_password", nullable = false)
    private String password;

    @Column(name = "db_firstname", nullable = false)
    private String firstName;

    @Column(name = "db_lastname")
    private String lastName;

    @Column(name = "db_phonenumber", unique = true, length = 13)
    private String phone;

    @Column(name = "db_address", nullable = false)
    private String address;

    @ManyToMany
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
