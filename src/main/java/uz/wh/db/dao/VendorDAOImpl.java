package uz.wh.db.dao;

import org.springframework.stereotype.Service;
import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.dao.interfaces.VendorDAO;
import uz.wh.db.entities.Vendor;
import uz.wh.db.repositories.VenderRepository;

import java.util.List;

@Service
public class VendorDAOImpl implements VendorDAO {
    VenderRepository venderRepository;

    public VendorDAOImpl(VenderRepository venderRepository) {
        this.venderRepository = venderRepository;

    }

    @Override
    public List<Vendor> getAll() {
        return venderRepository.findAll();
    }

    @Override
    public ObjectAndMessage saveEditVendor(Vendor vendor) {
        Vendor saved;
        ObjectAndMessage vendorAndMessage = new ObjectAndMessage();
        Vendor temp = venderRepository.findById(vendor.getId());
        if (temp != null) {
            temp.setDeleted(false);
            temp.setName(vendor.getName());
            temp.setPhone(vendor.getPhone());
            temp.setAddress(vendor.getAddress());
            saved = venderRepository.save(temp);
            vendorAndMessage.setMessage("Vendor has been updated!");
        } else {
            saved = venderRepository.save(vendor);
            vendorAndMessage.setMessage("Vendor has been created!");
        }
        vendorAndMessage.setObject(saved);
        return vendorAndMessage;
    }

    @Override
    public ObjectAndMessage deleteVendorById(int id) {
        ObjectAndMessage  objectAndMessage=new ObjectAndMessage();
        Vendor vendor = venderRepository.findById(id);
        vendor.setDeleted(true);
        venderRepository.save(vendor);
        objectAndMessage.setObject(null);
        objectAndMessage.setMessage("Vendor has been deleted!");
        return objectAndMessage;

    }
}
