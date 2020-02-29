package uz.wh.db.dao.interfaces;

import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.dto.OrderWithItemsDTO;
import uz.wh.db.entities.documentation.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderDAO {

    List<Order> getAll();
    Order getLastOrderForUser(int userId);
    Order getByDocumentNo(String documentNo);
    Order getByCustomerId(int id);
    Order getByOrderedDate(LocalDateTime date);
    ObjectAndMessage save(OrderWithItemsDTO order);
    ObjectAndMessage deleteById(int id);

}
