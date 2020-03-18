package uz.wh.db.dao.interfaces;

import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.dto.documents_dto.OutgoWithItemsDTO;
import uz.wh.db.entities.documents.Outgo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OutgoDAO {

    List<Outgo> getAll();
    Outgo getLastOutgoForCustomer(int customerId);
    Outgo getByDocumentNo(String documentNo);
    Outgo getByCustomerId(int id);
    Outgo getByOrderedDate(LocalDate date);
    ObjectAndMessage save(OutgoWithItemsDTO outgo);
    ObjectAndMessage deleteById(int id);

}
