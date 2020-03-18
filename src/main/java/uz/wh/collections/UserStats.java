package uz.wh.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.wh.db.entities.documents.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserStats {

    private int userId;
    private double overallPayment;
    private Payment lastPayment;
    private Order lastOrder;
    private Outgo lastOutgo;
    private Income lastIncome;
    private ReturnProduct lastReturn;

}
