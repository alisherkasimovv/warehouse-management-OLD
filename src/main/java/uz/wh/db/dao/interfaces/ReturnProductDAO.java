package uz.wh.db.dao.interfaces;

import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.dto.documents_dto.ReturnWithItemsDTO;
import uz.wh.db.entities.documents.ReturnProduct;

import java.util.List;

public interface ReturnProductDAO {

    List<ReturnProduct> getAll();
    ReturnProduct getLastReturnByCustomer(int customerId);
    ReturnProduct getByDocumentNo(String documentNo);
    ReturnProduct getByCustomerId(int id);
    ObjectAndMessage save(ReturnWithItemsDTO returnProduct);
    ObjectAndMessage deleteById(int id);

}
