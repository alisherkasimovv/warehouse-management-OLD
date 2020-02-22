package uz.wh.db.dao;

import org.springframework.stereotype.Service;
import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.dao.interfaces.ItemDAO;
import uz.wh.db.dao.interfaces.OutgoDAO;
import uz.wh.db.dto.OutgoWithItemsDTO;
import uz.wh.db.entities.documentation.Outgo;
import uz.wh.db.repositories.OutgoRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OutgoDAOImpl implements OutgoDAO {

    private OutgoRepository repository;
    private ItemDAO itemDAO;

    public OutgoDAOImpl(OutgoRepository repository,ItemDAO itemDAO) {
        this.repository=repository;
        this.itemDAO=itemDAO;
    }

    @Override
    public List<Outgo> getAll() {
        return repository.findAll();
    }

    @Override
    public Outgo getByDocumentNo(String documnetNo) {
        return repository.findByDocumentNo(documnetNo);
    }

    @Override
    public Outgo getByCustomerId(int id) {
        return repository.findByCustomerId(id);
    }

    @Override
    public Outgo getByOrderedDate(LocalDateTime date) {
        return repository.findByOrderDate(date);
    }

    @Override
    public ObjectAndMessage save(OutgoWithItemsDTO outgo) {
        Outgo saved;
        ObjectAndMessage objectAndMessage = new ObjectAndMessage();
        Outgo temp = outgo.getOutgo();
        itemDAO.saveItemList(
                outgo.getItems(),
                outgo.getOutgo().getDocumentNo(),
                outgo.getOutgo().getDocumentType(),
                outgo.getWarehouseId()
        );

//        if (temp != null) {
//            temp.setDeleted(false);
//            temp.setDocumentNo(outgo.getDocumentNo());
//            temp.setCustomerId(outgo.getCustomerId());
//            temp.setPrice(outgo.getPrice());
//
//            saved = repository.save(temp);
//            objectAndMessage.setMessage("Outgo has been updated!");
//        } else {
            saved =repository.save(temp);
            objectAndMessage.setMessage("Newe Outgo has been created!");
//        }
        objectAndMessage.setObject(saved);
        return objectAndMessage;
    }

    @Override
    public ObjectAndMessage deleteById(int id) {
        Outgo outgo=repository.findById(id);
        ObjectAndMessage objectAndMessage = new ObjectAndMessage();
        objectAndMessage.setMessage("Outgo has been deleted!");
        objectAndMessage.setObject(null);
        repository.deleteById(id);

        return objectAndMessage;
    }
}
