package uz.wh.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.wh.db.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String role);

}
