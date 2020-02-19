package uz.wh.db.dao;

import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.dao.interfaces.OutgoDAO;
import uz.wh.db.entities.documentation.Order;
import uz.wh.db.entities.documentation.Outgo;
import uz.wh.db.repositories.OutgoRepository;

import java.time.LocalDateTime;
import java.util.List;

public class OutputDAOImpl implements OutgoDAO {

    OutgoRepository repository;
    public OutputDAOImpl(OutgoRepository repository) {
        this.repository=repository;

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
        return repository.findByDate(date);
    }

    @Override
    public ObjectAndMessage save(Outgo outgo) {
        Outgo saved;
        ObjectAndMessage objectAndMessage = new ObjectAndMessage();
        Outgo temp = repository.findById(outgo.getId());

        if (temp != null) {
            temp.setDeleted(false);
            temp.setDocumentNo(outgo.getDocumentNo());
            temp.setCustomerId(outgo.getCustomerId());
            temp.setPrice(outgo.getPrice());

            saved = repository.save(temp);
            objectAndMessage.setMessage("Outgo has been updated!");
        } else {
            saved =repository.save(outgo);
            objectAndMessage.setMessage("Newe Outgo has been created!");
        }
        objectAndMessage.setObject(saved);
        return objectAndMessage;
    }

    @Override
    public ObjectAndMessage deleteById(int id) {
        ObjectAndMessage objectAndMessage = new ObjectAndMessage();
        objectAndMessage.setMessage("Outgo has been deleted!");
        objectAndMessage.setObject(null);
        repository.deleteById(id);

        return null;
    }
}
