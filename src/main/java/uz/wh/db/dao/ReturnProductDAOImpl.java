package uz.wh.db.dao;

import org.springframework.stereotype.Service;
import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.dao.interfaces.ItemDAO;
import uz.wh.db.dao.interfaces.ReturnProductDAO;
import uz.wh.db.dto.ReturnWithItemsDTO;
import uz.wh.db.entities.documentation.Outgo;
import uz.wh.db.entities.documentation.ReturnProduct;
import uz.wh.db.repositories.ReturnProductRepository;

import java.util.List;
@Service
public class ReturnProductDAOImpl implements ReturnProductDAO {
   ReturnProductRepository repository;
   ItemDAO itemDAO;
    public ReturnProductDAOImpl( ReturnProductRepository repository1,ItemDAO itemDAO) {
        repository=repository1;
        this.itemDAO=itemDAO;
    }

    @Override
    public List<ReturnProduct> getAll() {
        return repository.findAll();
    }

    @Override
    public ReturnProduct getByDocumentNo(String documnetNo) {
        return repository.findByDocumentNo(documnetNo);
    }

    @Override
    public ReturnProduct getByCustomerId(int id) {
        return repository.findByCustomerId(id);
    }

    @Override
    public ObjectAndMessage save(ReturnWithItemsDTO returnProduct) {

        ReturnProduct  saved;
        ObjectAndMessage objectAndMessage = new ObjectAndMessage();
       ReturnProduct temp =returnProduct.getReturnProduct();
        itemDAO.saveItemList(returnProduct.getItems(), returnProduct.getReturnProduct().getDocumentNo());

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
        objectAndMessage.setMessage("The product has been returned!");
//        }
        objectAndMessage.setObject(saved);
        return objectAndMessage;
    }

    @Override
    public ObjectAndMessage deleteById(int id) {
        ObjectAndMessage objectAndMessage = new ObjectAndMessage();
        objectAndMessage.setMessage("The returned products has been removed to the list.");
        objectAndMessage.setObject(null);
        repository.deleteById(id);

        return objectAndMessage;
    }
}
