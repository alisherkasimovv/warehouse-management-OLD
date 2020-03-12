package uz.wh.db.dao;

import org.springframework.stereotype.Service;
import uz.wh.collections.ObjectAndMessage;
import uz.wh.collections.ItemOnWarehouse;
import uz.wh.db.dao.interfaces.WarehouseDAO;
import uz.wh.db.dao.interfaces.WarehouseItemDAO;
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

    private WarehouseRepository repository;
    private WarehouseItemDAO itemDAO;
    private EntityManager em;

    public WarehouseDAOImpl(WarehouseRepository repository, WarehouseItemDAO itemDAO) {
        this.repository = repository;
        this.itemDAO = itemDAO;
    }

    @Override
    public Warehouse getById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Warehouse> getAll() {
        return repository.findAll();
    }

    @Override
    public ObjectAndMessage saveWarehouse(Warehouse warehouse) {
        Warehouse saved;
        ObjectAndMessage oam = new ObjectAndMessage();
        Warehouse temp = repository.findById(warehouse.getId());
        if (temp != null) {
            temp = warehouse;
            saved = repository.save(temp);
            oam.setMessage(saved.getName() + " nomli ombor yangilandi.");
        } else {
            saved = repository.save(warehouse);
            oam.setMessage("Yangi ombor saqlandi.");
        }

        oam.setObject(saved);
        return oam;
    }

    @Override
    public String deleteWarehouse(int id) {
        double count = itemDAO.countAllItemsByWarehouse(id);
        if (count == 0) {
            repository.deleteById(id);
            return "Tanlangan ombor ma'lumotlar bazasidan o'chirildi.";
        }
        return "Diqqat! Ombor bo'sh emas! Unda jami " + count + " mahsulot bor." +
                "Omborni o'chirishdan oldin undagi mahsulotlarni boshqa omborlarga ko'chirish lozim.";
    }

    @Override
    @Transactional
    public List<ItemOnWarehouse> countProductsOnAllWarehouses() {
        List<ItemOnWarehouse> itemOnWarehouseList = new ArrayList<>();

        String QUERY = "SELECT p.id, p.name, w.id, w.quantity, w.cost, w.price " +
                "FROM Product p " +
                "INNER JOIN WarehouseItem w ON w.productId = p.id";

        Query query = em.createQuery(QUERY);

        List<Object[]> resultset = query.getResultList();

        if (resultset.size() > 0) {
            for (Object[] obj : resultset) {
                ItemOnWarehouse itemOnWarehouse = new ItemOnWarehouse();

                itemOnWarehouse.setProductId((int) obj[0]);
                itemOnWarehouse.setProductName((String) (obj[1]));
                itemOnWarehouse.setWarehouseId((int) obj[2]);
                itemOnWarehouse.setWarehouseName((String) obj[3]);
                itemOnWarehouse.setQuantity((double) obj[4]);
                itemOnWarehouse.setCost((double) obj[5]);
                itemOnWarehouse.setPrice((double) obj[6]);

                double total;
                total = (double) obj[5] * (double) obj[4];
                itemOnWarehouse.setTotalCost(total);
                total = (double) obj[6] * (double) obj[4];
                itemOnWarehouse.setTotalPrice(total);

                itemOnWarehouseList.add(itemOnWarehouse);
            }
        }

        return itemOnWarehouseList;
    }

    /**
     * Count one chosen item quantity on all warehouses.
     *
     * @param productId Chosen product id.
     * @return Overall quantity of the product.
     */
    @Override
    public List<ItemOnWarehouse> countOneProductOnAllWarehouses(int productId) {

        List<ItemOnWarehouse> iow = new ArrayList<>();

        String QUERY = "SELECT p.id, p.name, w.id, w.quantity, w.cost, w.price " +
                "FROM Product p " +
                "INNER JOIN WarehouseItem w ON w.productId = p.id " + "WHERE p.id=" + productId;

        Query query = em.createQuery(QUERY);

        List<Object[]> resultset = query.getResultList();

        if (resultset.size() > 0) {
            for (Object[] obj : resultset) {

                ItemOnWarehouse itemOnWarehouse = new ItemOnWarehouse();

                itemOnWarehouse.setProductId((int) obj[0]);
                itemOnWarehouse.setProductName((String) obj[1]);
                itemOnWarehouse.setWarehouseId((int) obj[2]);
                itemOnWarehouse.setWarehouseName((String) obj[3]);
                itemOnWarehouse.setQuantity((double) obj[4]);
                itemOnWarehouse.setCost((double) obj[5]);
                itemOnWarehouse.setPrice((double) obj[6]);

                double total;
                total = (double) obj[5] * (double) obj[4];
                itemOnWarehouse.setTotalCost(total);
                total = (double) obj[6] * (double) obj[4];
                itemOnWarehouse.setTotalPrice(total);

                iow.add(itemOnWarehouse);
            }
        }

        return iow;
    }

    /**
     * Method calculates count of all items in the warehouse and sends back
     * list of these items.
     *
     * @param warehouseId Chosen warehouse id.
     * @return List of items with their count.
     */
    @Override
    public List<ItemOnWarehouse> countAllProductsOnOneWarehouse(int warehouseId) {

        List<ItemOnWarehouse> itemOnWarehouseList = new ArrayList<>();

        String QUERY = "SELECT p.id, p.name, w.id, w.quantity, w.cost, w.price " +
                "FROM Product p " +
                "INNER JOIN WarehouseItem w ON w.productId=p.id " + "WHERE w.id=" + warehouseId;

        Query query = em.createQuery(QUERY);

        List<Object[]> resultset = query.getResultList();

        if (resultset.size() > 0) {
            for (Object[] obj : resultset) {
                ItemOnWarehouse itemOnWarehouse = new ItemOnWarehouse();

                itemOnWarehouse.setProductId((int) obj[0]);
                itemOnWarehouse.setProductName((String) obj[1]);
                itemOnWarehouse.setWarehouseId((int) obj[2]);
                itemOnWarehouse.setWarehouseName((String) obj[3]);
                itemOnWarehouse.setQuantity((double) obj[4]);
                itemOnWarehouse.setCost((double) obj[5]);
                itemOnWarehouse.setPrice((double) obj[6]);

                double total;
                total = (double) obj[5] * (double) obj[4];
                itemOnWarehouse.setTotalCost(total);
                total = (double) obj[6] * (double) obj[4];
                itemOnWarehouse.setTotalPrice(total);

                itemOnWarehouseList.add(itemOnWarehouse);
            }
        }

        return itemOnWarehouseList;
    }

    @Override
    public ItemOnWarehouse countOneProductOnOneWarehouse(int productId, int warehouseId) {
        ItemOnWarehouse itemOnWarehouse = new ItemOnWarehouse();

        String QUERY = "SELECT p.id, p.name, w.id, w.quantity, w.cost, w.price " +
                "FROM Product p " +
                "INNER JOIN WarehouseItem w ON w.productId=p.id " + "WHERE p.id=" + productId + ",w.id=" + warehouseId;

        Query query = em.createQuery(QUERY);

        List<Object[]> resultset = query.getResultList();

        if (resultset.size() > 0) {
            for (Object[] obj : resultset) {

                itemOnWarehouse.setProductId((int) obj[0]);
                itemOnWarehouse.setProductName((String) obj[1]);
                itemOnWarehouse.setWarehouseId((int) obj[2]);
                itemOnWarehouse.setWarehouseName((String) obj[3]);
                itemOnWarehouse.setQuantity((double) obj[4]);
                itemOnWarehouse.setCost((double) obj[5]);
                itemOnWarehouse.setPrice((double) obj[6]);

                double total;
                total = (double) obj[5] * (double) obj[4];
                itemOnWarehouse.setTotalCost(total);
                total = (double) obj[6] * (double) obj[4];
                itemOnWarehouse.setTotalPrice(total);

            }
        }
        return itemOnWarehouse;
    }

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

}
