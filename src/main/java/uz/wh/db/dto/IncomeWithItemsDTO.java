package uz.wh.db.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.wh.db.entities.documentation.Income;
import uz.wh.db.entities.documentation.Item;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class IncomeWithItemsDTO {
    private Income income;
    private List<Item> items;
    private int warehouseId;
}
