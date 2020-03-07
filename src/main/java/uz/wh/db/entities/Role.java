package uz.wh.db.entities;

import lombok.Data;
//import org.springframework.security.core.GrantedAuthority;
import uz.wh.db.enums.UserType;

import javax.persistence.*;

@Data
@Entity(name = "db_roles")
public class Role  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private UserType type;

//    @Override
//    public String getAuthority() {
//        return type.name();
//    }
}
