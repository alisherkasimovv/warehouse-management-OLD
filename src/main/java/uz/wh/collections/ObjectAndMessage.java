package uz.wh.collections;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.wh.db.entities.Vendor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjectAndMessage {
    private Object object;
    private  String message;
}
