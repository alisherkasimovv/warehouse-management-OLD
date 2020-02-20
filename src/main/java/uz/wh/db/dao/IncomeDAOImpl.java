package uz.wh.db.dao;

import org.springframework.stereotype.Service;
import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.dao.interfaces.IncomeDAO;
import uz.wh.db.entities.documentation.Income;
import uz.wh.db.repositories.IncomeRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IncomeDAOImpl implements IncomeDAO {

    IncomeRepository repository;

    public IncomeDAOImpl(IncomeRepository incomeRepository) {
        repository = incomeRepository;
    }

    @Override
    public List<Income> getAll() {
        return repository.findAll();
    }

    @Override
    public Income getByDate(LocalDateTime date) {
        return repository.findByOrderDate(date);
    }

    @Override
    public Income getByDocumentNo(String documnetNo) {
        return repository.findByDocumentNo(documnetNo);
    }

    @Override
    public Income getByVendorId(int id) {
        return repository.findByVendorId(id);
    }

    @Override
    public ObjectAndMessage save(Income income) {
        Income saved;
        ObjectAndMessage objectAndMessage = new ObjectAndMessage();
        Income temp = repository.findById(income.getId());

        if (temp != null) {
            temp.setDeleted(false);
            temp.setBalance(income.getBalance());
            temp.setCost(income.getCost());
            temp.setDocumentNo(income.getDocumentNo());
            saved = repository.save(temp);
            objectAndMessage.setMessage("Income has been updated!");
        } else {
            saved = repository.save(income);
            objectAndMessage.setMessage("Income has been created!");
        }
        objectAndMessage.setObject(saved);
        return objectAndMessage;
    }

    @Override
    public ObjectAndMessage deleteById(int id) {
        ObjectAndMessage objectAndMessage = new ObjectAndMessage();
        objectAndMessage.setMessage("Income has been deleted!");
        objectAndMessage.setObject(null);
        repository.deleteById(id);
        return objectAndMessage;
    }


}
