package uz.wh.db.dao;

import org.springframework.stereotype.Service;
import uz.wh.collections.UserStats;
import uz.wh.db.dao.interfaces.*;

@Service
public class StatsCollector {

    private IncomeDAO incomeDAO;
    private OrderDAO orderDAO;
    private OutgoDAO outgoDAO;
    private PaymentDAO paymentDAO;
    private ReturnProductDAO returnDAO;

    public StatsCollector(
            IncomeDAO incomeDAO,
            OrderDAO orderDAO,
            OutgoDAO outgoDAO,
            PaymentDAO paymentDAO,
            ReturnProductDAO returnDAO) {
        this.incomeDAO = incomeDAO;
        this.orderDAO = orderDAO;
        this.outgoDAO = outgoDAO;
        this.paymentDAO = paymentDAO;
        this.returnDAO = returnDAO;
    }

    public UserStats collectStatsForUser(int userId) {
        UserStats userStats = new UserStats();

        userStats.setUserId(userId);
        userStats.setLastIncome(incomeDAO.getLastIncomeForVendor(userId));
        userStats.setLastOrder(orderDAO.getLastOrderForUser(userId));
        userStats.setLastOutgo(outgoDAO.getLastOutgoForCustomer(userId));
        userStats.setLastPayment(paymentDAO.getLastPaymentForUser(userId));
        userStats.setLastReturn(returnDAO.getLastReturnByCustomer(userId));

        return userStats;
    }

}
