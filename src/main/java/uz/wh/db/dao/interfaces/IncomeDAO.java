package uz.wh.db.dao.interfaces;

import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.dto.IncomeWithItemsDTO;
import uz.wh.db.entities.documentation.Income;

import java.time.LocalDateTime;
import java.util.List;

public interface IncomeDAO {

    List <Income> getAll();

    Income getByDocumentNo(String documnetNo);

    Income getByVendorId(int id);

    Income getByDate(LocalDateTime date);

    ObjectAndMessage save(IncomeWithItemsDTO incomeWithItemsDTO);

    ObjectAndMessage deleteById(int id);
}
