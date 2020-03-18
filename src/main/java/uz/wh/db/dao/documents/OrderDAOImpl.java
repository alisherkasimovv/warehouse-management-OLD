package uz.wh.db.dao.documents;

import org.springframework.stereotype.Service;
import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.dao.interfaces.OrderDAO;
import uz.wh.db.dto.documents_dto.OrderWithItemsDTO;
import uz.wh.db.entities.documents.Order;
import uz.wh.db.entities.documents.items.OrderItem;
import uz.wh.db.repositories.documents.OrderItemRepository;
import uz.wh.db.repositories.documents.OrderRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class OrderDAOImpl implements OrderDAO {

    private OrderRepository repository;
    private OrderItemRepository itemRepository;

    public OrderDAOImpl(OrderRepository repository, OrderItemRepository itemRepository) {
        this.repository=repository;
        this.itemRepository = itemRepository;
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
    public Order getByOrderedDate(LocalDate date) {
        return repository.findByDocumentDate(date);
    }

    @Override
    public ObjectAndMessage save(OrderWithItemsDTO order) {
        Order saved;
        ObjectAndMessage objectAndMessage = new ObjectAndMessage();
        this.registerAndSaveOrderItem(order.getItems());

        Order temp = repository.findById(order.getOrder().getId());

        if (temp != null) {
            temp.setDeleted(false);
            temp.setBalance(order.getOrder().getBalance());
            temp.setDocumentNo(order.getOrder().getDocumentNo());
            temp.setCustomerId(order.getOrder().getCustomerId());

            saved = repository.save(temp);
            objectAndMessage.setMessage("Buyurtma ma'lumotlari yangilandi");
        } else {
            saved =repository.save(order.getOrder());
            objectAndMessage.setMessage("Yangi buyurtma ro'yhatdan o'tkazildi");
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

    private void registerAndSaveOrderItem(List<OrderItem> list) {
        this.itemRepository.saveAll(list);
    }

}
