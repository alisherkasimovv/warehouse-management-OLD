package uz.wh.db.dao.interfaces;

import uz.wh.collections.VendorAndMessage;
import uz.wh.db.entities.Vendor;

import java.util.List;

public interface VendorDAO {
    List<Vendor> getAll();
    VendorAndMessage saveEditVendor(Vendor vendor);
    String deleteVendorById(int id);
}
