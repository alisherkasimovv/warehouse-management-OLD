package uz.wh.db.dao;

import org.springframework.stereotype.Service;
import uz.wh.collections.UserStats;
import uz.wh.db.dao.interfaces.*;
import uz.wh.db.dto.ProductWithWarehouseQtyDTO;
import uz.wh.db.dto.WarehouseCountDTO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataCollector {

    private IncomeDAO incomeDAO;
    private OrderDAO orderDAO;
    private OutgoDAO outgoDAO;
    private PaymentDAO paymentDAO;
    private ReturnProductDAO returnDAO;
    private EntityManager em;

    public DataCollector(
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

    @Transactional
    public List<ProductWithWarehouseQtyDTO> collectAllProductsAndTheirCounts() {
        List<ProductWithWarehouseQtyDTO> products = new ArrayList<>();

        String QUERY = "SELECT prod.id, prod.name, prod.description, prod.meansurement, dwi.quantity, dw.name " +
                        "FROM Product prod " +
                        "INNER JOIN WarehouseItem dwi on prod.id = dwi.productId " +
                        "INNER JOIN Warehouse dw on dwi.warehouseId = dw.id " +
                        "ORDER BY prod.id DESC";
        Query query = em.createQuery(QUERY);
        List<Object[]> resultList = query.getResultList();

        int currentId = 0;
        if (resultList.size() > 0) {
            for (Object[] obj : resultList) {
                if ((int) obj[0] != currentId) {
                    ProductWithWarehouseQtyDTO pww = new ProductWithWarehouseQtyDTO();
                    currentId = (int) obj[0];

                    pww.setProductId((int) obj[0]);
                    pww.setProductName((String) obj[1]);
                    pww.setDescription((String) obj[2]);
                    pww.setMeasurement((String) obj[3]);

                    pww.setWarehouses(new WarehouseCountDTO((String) obj[5], (double) obj[4]));

                    products.add(pww);
                } else {
                    products.get(products.size() - 1).setWarehouses(new WarehouseCountDTO((String) obj[5], (double) obj[4]));
                }
            }
        }

        return products;
    }

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }
}
