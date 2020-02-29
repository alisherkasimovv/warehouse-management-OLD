package uz.wh.db.dao;

import org.springframework.stereotype.Service;
import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.dao.interfaces.ItemDAO;
import uz.wh.db.dao.interfaces.OrderDAO;
import uz.wh.db.dto.OrderWithItemsDTO;
import uz.wh.db.entities.documentation.Order;
import uz.wh.db.repositories.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class OrderDAOImpl implements OrderDAO {

    private OrderRepository repository;
    private ItemDAO itemDAO;

    public OrderDAOImpl(OrderRepository repository, ItemDAO itemDAO) {
        this.repository=repository;
        this.itemDAO = itemDAO;
    }

    @Override
    public List<Order> getAll() {
        return repository.findAll();
    }

    @Override
    public Order getLastOrderForUser(int userId) {
        return repository.findDistinctFirstByCustomerIdAndDeletedFalseOrderByIdDesc(userId);
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
        return repository.findByOrderDate(date);
    }

    @Override
    public ObjectAndMessage save(OrderWithItemsDTO order) {
        Order saved;
        ObjectAndMessage objectAndMessage = new ObjectAndMessage();
        itemDAO.saveItemList(
                order.getItems(),
                order.getOrder().getDocumentNo(),
                order.getOrder().getDocumentType(),
                order.getWarehouseId()
        );

        Order temp = repository.findById(order.getOrder().getId());

        if (temp != null) {
            temp.setDeleted(false);
            temp.setBalance(order.getOrder().getBalance());
            temp.setDocumentNo(order.getOrder().getDocumentNo());
            temp.setCustomerId(order.getOrder().getCustomerId());

            saved = repository.save(temp);
            objectAndMessage.setMessage("Order has been updated!");
        } else {
            saved =repository.save(order.getOrder());
            objectAndMessage.setMessage("New Order has been created!");
        }
        objectAndMessage.setObject(saved);
        return objectAndMessage;
    }

    @Override
    public ObjectAndMessage deleteById(int id) {
        Order order=repository.findById(id);
        ObjectAndMessage objectAndMessage = new ObjectAndMessage();
        objectAndMessage.setMessage("Order has been deleted!");
        order.setDeleted(true);
        objectAndMessage.setObject(null);
        repository.save(order);
        return objectAndMessage;
    }
}
