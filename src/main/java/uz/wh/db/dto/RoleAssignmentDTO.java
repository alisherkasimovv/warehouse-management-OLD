package uz.wh.db.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Object contains user's ID which will be enriched
 * with chosen roles from this object.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleAssignmentDTO {

    private int userId;
    private String role;

}
