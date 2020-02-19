package uz.wh.db.dao.interfaces;

import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.entities.documentation.Order;
import uz.wh.db.entities.documentation.Outgo;

import java.time.LocalDateTime;
import java.util.List;

public interface OutgoDAO {
    List<Outgo> getAll();

    Outgo getByDocumentNo(String documnetNo);

    Outgo getByCustomerId(int id);

    Outgo getByOrderedDate(LocalDateTime date);

    ObjectAndMessage save(Outgo outgo);

    ObjectAndMessage deleteById(int id);

}
