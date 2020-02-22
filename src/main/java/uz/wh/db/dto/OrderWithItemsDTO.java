package uz.wh.db.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.wh.db.entities.documentation.Item;
import uz.wh.db.entities.documentation.Order;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class OrderWithItemsDTO {
    private Order order;
    private List<Item> items;
    private int warehouseId;
}
