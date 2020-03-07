package uz.wh.db.dao;

import org.springframework.stereotype.Service;
import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.dao.interfaces.PaymentDAO;
import uz.wh.db.dao.interfaces.UserDAO;
import uz.wh.db.dao.interfaces.VendorDAO;
import uz.wh.db.entities.documentation.Payment;
import uz.wh.db.enums.PaymentType;
import uz.wh.db.repositories.PaymentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentDAOImpl implements PaymentDAO {

    private PaymentRepository repository;
    private EntityManager entityManager;

    public PaymentDAOImpl(PaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Payment> getAllAvailablePayments() {
        return repository.findAllByDeletedFalse();
    }

    @Override
    public List<Payment> getAllDeletedPayments() {
        return repository.findAllByDeletedTrue();
    }

    @Override
    public List<Payment> getAllPaymentsForPayer(int payerId) {
        return repository.findAllByPayerId(payerId);
    }

    @Override
    public List<Payment> filterByPaymentType(PaymentType paymentType) {
        return repository.findAllByPaymentType(paymentType);
    }

    @Override
    public List<Payment> calculateSumForAllInstances() {
        List<Payment> paymentsFromCustomers = new ArrayList<>();
//        String CUSTOMER_QUERY = "SELECT "

//        Query query = entityManager.createQuery(CUSTOMER_QUERY);

        return null;
    }

    @Override
    public Payment getLastPaymentForUser(int payerId) {
        return repository.findDistinctFirstByPayerIdOrderByIdDesc(payerId);
    }

    @Override
    public ObjectAndMessage acceptPayment(Payment payment) {
        ObjectAndMessage objectAndMessage = new ObjectAndMessage();
        objectAndMessage.setMessage("To'lov qabul qilindi!");
        objectAndMessage.setObject(repository.save(payment));
        return objectAndMessage;
    }

    @Override
    public double getAllPaymentsForOnePayer(int payerId) {
        return repository.sumUpAllPaymentsByPayer(payerId);
    }

    @Override
    public String deletePayment(int paymentId) {
        repository.deleteById(paymentId);
        return "To'lov hisobdan chiqarildi";
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
