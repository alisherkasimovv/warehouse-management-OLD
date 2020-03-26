package uz.wh.db.dao;

import org.springframework.stereotype.Service;
import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.dao.interfaces.CustomerDAO;
import uz.wh.db.entities.Customer;
import uz.wh.db.repositories.CustomerRepository;

import java.util.List;

@Service
public class CustomerDAOImpl implements CustomerDAO {

    private CustomerRepository repository;

    public CustomerDAOImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Customer> getAll() {
        return repository.findAllByDeletedFalse();
    }

    @Override
    public List<Customer> getAllDeleted() {
        return repository.findAllByDeletedTrue();
    }

    @Override
    public Customer getById(int id) {
        return repository.findById(id);
    }

    @Override
    public ObjectAndMessage saveCustomer(Customer customer) {
        Customer saved;
        ObjectAndMessage objectAndMessage = new ObjectAndMessage();
        Customer temp = repository.findById(customer.getId());

        if (temp != null) {
            temp = customer;
            temp.setDeleted(false);
            saved = repository.save(temp);
            objectAndMessage.setMessage("Xaridor yangilandi!");
        } else {
            saved = repository.save(customer);
            objectAndMessage.setMessage("Yangi xaridor yaratildi!");
        }

        objectAndMessage.setObject(saved);
        return objectAndMessage;
    }

    @Override
    public String deleteCustomer(int id) {
        Customer customer = repository.findById(id);
        customer.setDeleted(true);
        repository.save(customer);
        return "Xaridor o'chirildi";
    }
}
