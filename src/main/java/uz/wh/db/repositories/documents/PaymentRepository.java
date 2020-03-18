package uz.wh.db.repositories.documents;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.wh.db.entities.documents.Payment;
import uz.wh.db.enums.PaymentType;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    List<Payment> findAllByDeletedFalse();
    List<Payment> findAllByDeletedTrue();
    List<Payment> findAllByPaymentType(PaymentType paymentType);
    List<Payment> findAllByPayerId(int payerId);

    Payment findById(int id);
    Payment findDistinctFirstByPayerIdOrderByIdDesc(int payerId);

    @Query("SELECT sum(p.amount) FROM Payment p WHERE p.payerId = :payerId")
    double sumUpAllPaymentsByPayer(int payerId);

}
