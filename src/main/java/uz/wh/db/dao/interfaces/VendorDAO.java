package uz.wh.db.dao.interfaces;

import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.entities.Vendor;

import java.util.List;

public interface VendorDAO {
    List<Vendor> getAll();
    ObjectAndMessage saveEditVendor(Vendor vendor);
    String deleteVendorById(int id);
}
