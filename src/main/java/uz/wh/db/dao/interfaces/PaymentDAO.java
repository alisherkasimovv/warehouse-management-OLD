package uz.wh.db.dao.interfaces;

import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.entities.documentation.Payment;
import uz.wh.db.enums.PaymentType;

import java.util.List;

public interface PaymentDAO {

    List<Payment> getAllAvailablePayments();
    List<Payment> getAllDeletedPayments();
    List<Payment> getAllPaymentsForPayer(int payerId);
    List<Payment> filterByPaymentType(PaymentType paymentType);
    List<Payment> calculateSumForAllInstances();

    ObjectAndMessage acceptPayment(Payment payment);
    String deletePayment(int paymentId);

}
