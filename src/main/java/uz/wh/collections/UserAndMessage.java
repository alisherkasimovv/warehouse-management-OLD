package uz.wh.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.wh.db.entities.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAndMessage{

    private User user;

    private String message;
}
