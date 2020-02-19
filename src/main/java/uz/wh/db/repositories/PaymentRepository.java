package uz.wh.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.wh.db.entities.documentation.Payment;
import uz.wh.db.enums.PaymentType;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    List<Payment> findAllByDeletedFalse();
    List<Payment> findAllByDeletedTrue();
    List<Payment> findAllByPaymentType(PaymentType paymentType);
    List<Payment> findAllByPayerId(int payerId);

    @Query("SELECT sum(p.amount) FROM Payment p WHERE p.payerId = :payerId")
    double sumUpAllPaymentsByPayer(int payerId);

}
