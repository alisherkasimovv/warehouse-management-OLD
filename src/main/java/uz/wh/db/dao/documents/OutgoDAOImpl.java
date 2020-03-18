package uz.wh.db.dao.documents;

import org.springframework.stereotype.Service;
import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.dao.interfaces.OutgoDAO;
import uz.wh.db.dao.interfaces.WarehouseItemDAO;
import uz.wh.db.dto.documents_dto.OutgoWithItemsDTO;
import uz.wh.db.entities.documents.Outgo;
import uz.wh.db.entities.documents.items.OutgoItem;
import uz.wh.db.repositories.documents.OutgoItemRepository;
import uz.wh.db.repositories.documents.OutgoRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class OutgoDAOImpl implements OutgoDAO {

    private OutgoRepository repository;
    private OutgoItemRepository itemRepository;
    private WarehouseItemDAO warehouseItemDAO;

    public OutgoDAOImpl(OutgoRepository repository, OutgoItemRepository itemRepository, WarehouseItemDAO warehouseItemDAO) {
        this.repository = repository;
        this.itemRepository = itemRepository;
        this.warehouseItemDAO = warehouseItemDAO;
    }

    @Override
    public List<Outgo> getAll() {
        return repository.findAll();
    }

    @Override
    public Outgo getLastOutgoForCustomer(int customerId) {
        return repository.findDistinctFirstByCustomerIdAndDeletedFalseOrderByIdDesc(customerId);
    }

    @Override
    public Outgo getByDocumentNo(String documentNo) {
        return repository.findByDocumentNo(documentNo);
    }

    @Override
    public Outgo getByCustomerId(int id) {
        return repository.findByCustomerId(id);
    }

    @Override
    public Outgo getByOrderedDate(LocalDate date) {
        return repository.findByDocumentDate(date);
    }

    @Override
    public ObjectAndMessage save(OutgoWithItemsDTO outgo) {
        Outgo saved;
        ObjectAndMessage objectAndMessage = new ObjectAndMessage();
        Outgo temp = outgo.getOutgo();

        this.registerAndSaveOutgoItem(outgo.getItems());
        saved = repository.save(temp);
        objectAndMessage.setMessage("Mahsulotlar chiqimi ro'yhatga olindi.");

        objectAndMessage.setObject(saved);
        return objectAndMessage;
    }

    @Override
    public ObjectAndMessage deleteById(int id) {
        Outgo outgo = repository.findById(id);
        ObjectAndMessage objectAndMessage = new ObjectAndMessage();
        objectAndMessage.setMessage("Outgo has been deleted!");
        objectAndMessage.setObject(null);
        repository.deleteById(id);

        return objectAndMessage;
    }

    private void registerAndSaveOutgoItem(List<OutgoItem> list) {
        for (OutgoItem item : list) {
            warehouseItemDAO.registerOutgoFromWarehouse(item);
        }

        itemRepository.saveAll(list);
    }

}
