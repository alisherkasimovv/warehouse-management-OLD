package uz.wh.db.dao.interfaces;

import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.entities.Customer;

import java.util.List;

public interface CustomerDAO {

    List<Customer> getAll();
    List<Customer> getAllDeleted();
    Customer getById(int id);
    ObjectAndMessage saveCustomer(Customer customer);
    String deleteCustomer(int id);

}
