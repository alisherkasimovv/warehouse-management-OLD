package uz.wh.db.dao;

import org.springframework.stereotype.Service;
import uz.wh.collections.WarehouseStatus;
import uz.wh.db.dao.interfaces.WarehouseDAO;
import uz.wh.db.entities.Warehouse;
import uz.wh.db.repositories.WarehouseRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class WarehouseDAOImpl implements WarehouseDAO {

    private WarehouseRepository warehouseRepository;
    private EntityManager em;

    public WarehouseDAOImpl(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }


    @Override
    public Warehouse getById(int id) {
        return warehouseRepository.findById(id);
    }

    @Override
    public List<Warehouse> getAll() {
        return warehouseRepository.findAll();
    }

    @Override
    @Transactional
    public List<WarehouseStatus> countProductsOnAllWarehouses() {
        List<WarehouseStatus> warehouseStatusList = new ArrayList<>();

        String QUERY = "SELECT p.id, p.name, w.id, w.name, w.quantity, w.cost, w.price " +
                "FROM Product p " +
                "INNER JOIN Warehouse w ON w.productId = p.id";

        Query query = em.createQuery(QUERY);

        List<Object[]> resultset = query.getResultList();

        if (resultset.size() > 0) {
            for (Object[] obj : resultset) {
                WarehouseStatus warehouseStatus = new WarehouseStatus();

                warehouseStatus.setProductId((int) obj[0]);
                warehouseStatus.setProductName((String) (obj[1]));
                warehouseStatus.setWarehouseId((int) obj[2]);
                warehouseStatus.setWarehouseName((String) obj[3]);
                warehouseStatus.setQuantity((double) obj[4]);
                warehouseStatus.setCost((double) obj[5]);
                warehouseStatus.setPrice((double) obj[6]);

                double total;
                total = (double) obj[5] * (double) obj[4];
                warehouseStatus.setTotalCost(total);
                total = (double) obj[6] * (double) obj[4];
                warehouseStatus.setTotalPrice(total);

                warehouseStatusList.add(warehouseStatus);
            }
        }

        return warehouseStatusList;
    }

    @Override
    public WarehouseStatus countOneProductOnAllWarehouses(int productId) {

        WarehouseStatus warehouseStatus = new WarehouseStatus();

        String QUERY = "SELECT p.id, p.name, w.id, w.name, w.quantity, w.cost, w.price " +
                "FROM Product p " +
                "INNER JOIN Warehouse w ON w.productId=p.id " + "WHERE p.id=" + productId;

        Query query = em.createQuery(QUERY);

        List<Object[]> resultset = query.getResultList();

        if (resultset.size() > 0) {
            for (Object[] obj : resultset) {

                warehouseStatus.setProductId((int) obj[0]);
                warehouseStatus.setProductName((String) obj[1]);
                warehouseStatus.setWarehouseId((int) obj[2]);
                warehouseStatus.setWarehouseName((String) obj[3]);
                warehouseStatus.setQuantity((double) obj[4]);
                warehouseStatus.setCost((double) obj[5]);
                warehouseStatus.setPrice((double) obj[6]);

                double total;
                total = (double) obj[5] * (double) obj[4];
                warehouseStatus.setTotalCost(total);
                total = (double) obj[6] * (double) obj[4];
                warehouseStatus.setTotalPrice(total);

            }
        }

        return warehouseStatus;
    }

    @Override
    public List<WarehouseStatus> countProductsOnOneWarehouse(int warehouseId) {

        List<WarehouseStatus> warehouseStatusList = new ArrayList<>();

        String QUERY = "SELECT p.id, p.name, w.id, w.name, w.quantity, w.cost, w.price " +
                "FROM Product p " +
                "INNER JOIN Warehouse w ON w.productId=p.id " + "WHERE w.id=" + warehouseId;

        Query query = em.createQuery(QUERY);

        List<Object[]> resultset = query.getResultList();

        if (resultset.size() > 0) {
            for (Object[] obj : resultset) {
                WarehouseStatus warehouseStatus = new WarehouseStatus();

                warehouseStatus.setProductId((int) obj[0]);
                warehouseStatus.setProductName((String) obj[1]);
                warehouseStatus.setWarehouseId((int) obj[2]);
                warehouseStatus.setWarehouseName((String) obj[3]);
                warehouseStatus.setQuantity((double) obj[4]);
                warehouseStatus.setCost((double) obj[5]);
                warehouseStatus.setPrice((double) obj[6]);

                double total;
                total = (double) obj[5] * (double) obj[4];
                warehouseStatus.setTotalCost(total);
                total = (double) obj[6] * (double) obj[4];
                warehouseStatus.setTotalPrice(total);

                warehouseStatusList.add(warehouseStatus);
            }
        }

        return warehouseStatusList;
    }

    @Override
    public WarehouseStatus countOneProductOnOneWarehouse(int productId, int warehouseId) {
        WarehouseStatus warehouseStatus = new WarehouseStatus();

        String QUERY = "SELECT p.id, p.name, w.id, w.name, w.quantity, w.cost, w.price " +
                "FROM Product p " +
                "INNER JOIN Warehouse w ON w.productId=p.id " + "WHERE p.id=" + productId + ",w.id=" + warehouseId;

        Query query = em.createQuery(QUERY);

        List<Object[]> resultset = query.getResultList();

        if (resultset.size() > 0) {
            for (Object[] obj : resultset) {

                warehouseStatus.setProductId((int) obj[0]);
                warehouseStatus.setProductName((String) obj[1]);
                warehouseStatus.setWarehouseId((int) obj[2]);
                warehouseStatus.setWarehouseName((String) obj[3]);
                warehouseStatus.setQuantity((double) obj[4]);
                warehouseStatus.setCost((double) obj[5]);
                warehouseStatus.setPrice((double) obj[6]);

                double total;
                total = (double) obj[5] * (double) obj[4];
                warehouseStatus.setTotalCost(total);
                total = (double) obj[6] * (double) obj[4];
                warehouseStatus.setTotalPrice(total);

            }
        }
        return warehouseStatus;
    }

}
