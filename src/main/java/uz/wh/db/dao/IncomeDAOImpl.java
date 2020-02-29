package uz.wh.db.dao;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.dao.interfaces.IncomeDAO;
import uz.wh.db.dao.interfaces.ItemDAO;
import uz.wh.db.dto.IncomeWithItemsDTO;
import uz.wh.db.entities.documentation.Income;
import uz.wh.db.repositories.IncomeRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IncomeDAOImpl implements IncomeDAO {

    IncomeRepository repository;
    ItemDAO itemDAO;
    public IncomeDAOImpl(IncomeRepository incomeRepository,ItemDAO itemDAO1) {
        repository = incomeRepository;
        itemDAO=itemDAO1;
    }

    @Override
    public List<Income> getAll() {
        return repository.findAll();
    }

    @Override
    public Income getLastIncomeForVendor(int vendorId) {
        return repository.findDistinctFirstByVendorIdAndDeletedFalseOrderByIdDesc(vendorId);
    }

    @Override
    public Income getByDate(LocalDateTime date) {
        return repository.findByOrderDate(date);
    }

    @Override
    public Income getByDocumentNo(String documentNo) {
        return repository.findByDocumentNo(documentNo);
    }

    @Override
    public Income getByVendorId(int id) {
        return repository.findByVendorId(id);
    }

    @Override
    public ObjectAndMessage save(IncomeWithItemsDTO incomeWithItems) {
        Income saved;
        ObjectAndMessage objectAndMessage = new ObjectAndMessage();
        itemDAO.saveItemList(
                incomeWithItems.getItems(),
                incomeWithItems.getIncome().getDocumentNo(),
                incomeWithItems.getIncome().getDocumentType(),
                incomeWithItems.getWarehouseId());

        Income temp = incomeWithItems.getIncome();

//        if (temp != null) {
//            temp.setDeleted(false);
//            temp.setBalance(income.getBalance());
//            temp.setCost(income.getCost());
//            temp.setDocumentNo(income.getDocumentNo());
//            saved = repository.save(temp);
//            objectAndMessage.setMessage("Income has been updated!");
//        } else {
            saved =repository.save(temp);
            objectAndMessage.setMessage("Income has been created!");
//        }
        objectAndMessage.setObject(saved);
        return objectAndMessage;
    }

    @Override
    public ObjectAndMessage deleteById(int id) {
        Income income=repository.findById(id);
        ObjectAndMessage objectAndMessage = new ObjectAndMessage();
        objectAndMessage.setMessage("Income has been deleted!");
        income.setDeleted(true);
        objectAndMessage.setObject(null);

        return objectAndMessage;
    }


}
