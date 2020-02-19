package uz.wh.db.dao;

import org.springframework.stereotype.Service;
import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.dao.interfaces.OrderDAO;
import uz.wh.db.entities.documentation.Income;
import uz.wh.db.entities.documentation.Order;
import uz.wh.db.repositories.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class OrderDAOImpl implements OrderDAO {

    OrderRepository repository;

    public OrderDAOImpl(OrderRepository repository) {
        this.repository=repository;
    }

    @Override
    public List<Order> getAll() {
        return repository.findAll();
    }

    @Override
    public Order getByDocumentNo(String documnetNo) {
        return repository.findByDocumentNo(documnetNo);
    }

    @Override
    public Order getByCustomerId(int id) {
        return repository.findByCustomerId(id);
    }

    @Override
    public Order getByOrderedDate(LocalDateTime date) {
        return getByOrderedDate(date);
    }

    @Override
    public ObjectAndMessage save(Order order) {
        Order saved;
        ObjectAndMessage objectAndMessage = new ObjectAndMessage();
        Order temp = repository.findById(order.getId());

        if (temp != null) {
            temp.setDeleted(false);
            temp.setBalance(order.getBalance());
            temp.setDocumentNo(order.getDocumentNo());
            temp.setCustomerId(order.getCustomerId());

            saved = repository.save(temp);
            objectAndMessage.setMessage("Order has been updated!");
        } else {
            saved =repository.save(order);
            objectAndMessage.setMessage("Newe Order has been created!");
        }
        objectAndMessage.setObject(saved);
        return objectAndMessage;
    }

    @Override
    public ObjectAndMessage deleteById(int id) {
        ObjectAndMessage objectAndMessage = new ObjectAndMessage();
        objectAndMessage.setMessage("Order has been deleted!");
        objectAndMessage.setObject(null);
        repository.deleteById(id);
        return objectAndMessage;
    }
}
