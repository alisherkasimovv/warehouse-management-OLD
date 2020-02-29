package uz.wh.db.dao.interfaces;

import org.springframework.data.domain.Page;
import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.dto.IncomeWithItemsDTO;
import uz.wh.db.entities.documentation.Income;

import java.time.LocalDateTime;
import java.util.List;

public interface IncomeDAO {

    List<Income> getAll();
    Income getLastIncomeForVendor(int vendorId);
    Income getByDocumentNo(String documentNo);
    Income getByVendorId(int id);
    Income getByDate(LocalDateTime date);
    ObjectAndMessage save(IncomeWithItemsDTO incomeWithItemsDTO);
    ObjectAndMessage deleteById(int id);

}
