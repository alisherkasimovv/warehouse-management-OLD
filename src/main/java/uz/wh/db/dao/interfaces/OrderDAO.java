package uz.wh.db.dao.interfaces;

import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.entities.documentation.Income;
import uz.wh.db.entities.documentation.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderDAO {

    List<Order> getAll();

    Order getByDocumentNo(String documnetNo);

    Order getByCustomerId(int id);

    Order getByOrderedDate(LocalDateTime date);

    ObjectAndMessage save(Order order);

    ObjectAndMessage deleteById(int id);
}
