package uz.wh.db.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.wh.db.entities.documentation.Item;
import uz.wh.db.entities.documentation.Outgo;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


public class OutgoWithItemsDTO {

    private Outgo outgo;

    private List<Item> items;
}
