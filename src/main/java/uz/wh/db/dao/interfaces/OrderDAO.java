package uz.wh.db.dao.interfaces;

import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.dto.documents_dto.OrderWithItemsDTO;
import uz.wh.db.entities.documents.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderDAO {

    List<Order> getAll();
    Order getLastOrderForUser(int userId);
    Order getByDocumentNo(String documentNo);
    Order getByCustomerId(int id);
    Order getByOrderedDate(LocalDate date);
    ObjectAndMessage save(OrderWithItemsDTO order);
    ObjectAndMessage deleteById(int id);

}
