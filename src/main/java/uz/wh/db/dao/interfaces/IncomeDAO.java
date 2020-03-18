package uz.wh.db.dao.interfaces;

import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.dto.documents_dto.IncomeWithItemsDTO;
import uz.wh.db.entities.documents.Income;

import java.time.LocalDate;
import java.util.List;

public interface IncomeDAO {

    List<Income> getAll();
    Income getLastIncomeForVendor(int vendorId);
    Income getByDocumentNo(String documentNo);
    Income getByVendorId(int id);
    Income getByDate(LocalDate date);
    ObjectAndMessage save(IncomeWithItemsDTO incomeWithItemsDTO);
    ObjectAndMessage deleteById(int id);

}
