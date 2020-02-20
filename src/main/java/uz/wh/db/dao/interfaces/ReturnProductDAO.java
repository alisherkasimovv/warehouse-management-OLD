package uz.wh.db.dao.interfaces;

import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.dto.OutgoWithItemsDTO;
import uz.wh.db.dto.ReturnWithItemsDTO;
import uz.wh.db.entities.documentation.Outgo;
import uz.wh.db.entities.documentation.ReturnProduct;

import java.time.LocalDateTime;
import java.util.List;

public interface ReturnProductDAO {
    List<ReturnProduct> getAll();

    ReturnProduct getByDocumentNo(String documnetNo);

    ReturnProduct getByCustomerId(int id);

    ObjectAndMessage save(ReturnWithItemsDTO returnProduct);

    ObjectAndMessage deleteById(int id);
}
