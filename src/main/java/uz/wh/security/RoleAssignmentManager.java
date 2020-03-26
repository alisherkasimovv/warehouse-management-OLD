package uz.wh.security;

import org.springframework.stereotype.Component;
import uz.wh.db.entities.Role;
import uz.wh.db.repositories.RoleRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleAssignmentManager {

    private RoleRepository repository;

    public RoleAssignmentManager(RoleRepository repository) {
        this.repository = repository;
    }

    public List<Role> assignAs(String assignment) {
        List<Role> roles = new ArrayList<>();

        switch (assignment) {
            case "ROLE_SUPERUSER": roles.add(repository.findByName("ROLE_SUPERUSER"));
            case "ROLE_DIRECTOR": roles.add(repository.findByName("ROLE_DIRECTOR"));
            case "ROLE_STOREKEEPER": roles.add(repository.findByName("ROLE_STOREKEEPER"));
            case "ROLE_USER":
                roles.add(repository.findByName("ROLE_USER"));
                break;
        }

        return roles;
    }
}
