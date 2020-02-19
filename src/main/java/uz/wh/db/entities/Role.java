package uz.wh.db.entities;

import lombok.Data;
//import org.springframework.security.core.GrantedAuthority;
import uz.wh.db.enums.UserTypes;

import javax.persistence.*;

@Data
@Entity(name = "db_roles")
public class Role  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private UserTypes type;

//    @Override
//    public String getAuthority() {
//        return type.name();
//    }
}
