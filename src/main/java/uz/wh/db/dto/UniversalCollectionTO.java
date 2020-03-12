package uz.wh.db.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.wh.db.entities.Product;
import uz.wh.db.entities.Warehouse;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UniversalCollectionTO {

    private List<Product> products;
    private List<Warehouse> warehouses;

}
