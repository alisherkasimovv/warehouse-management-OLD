package uz.wh.db.dao;

import org.springframework.stereotype.Service;
import uz.wh.collections.ObjectAndMessage;
import uz.wh.db.dao.interfaces.VendorDAO;
import uz.wh.db.entities.Vendor;
import uz.wh.db.repositories.VendorRepository;

import java.util.List;

@Service
public class VendorDAOImpl implements VendorDAO {
    VendorRepository vendorRepository;

    public VendorDAOImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;

    }

    @Override
    public List<Vendor> getAll() {
        return vendorRepository.findAll();
    }

    @Override
    public ObjectAndMessage saveEditVendor(Vendor vendor) {
        Vendor saved;
        ObjectAndMessage objectAndMessage = new ObjectAndMessage();
        Vendor temp = vendorRepository.findById(vendor.getId());
        if (temp != null) {
            temp.setDeleted(false);
            temp.setName(vendor.getName());
            temp.setPhone(vendor.getPhone());
            temp.setAddress(vendor.getAddress());
            saved = vendorRepository.save(temp);
            objectAndMessage.setMessage("Vendor has been updated!");
        } else {
            saved = vendorRepository.save(vendor);
            objectAndMessage.setMessage("Vendor has been created!");
        }
        objectAndMessage.setObject(saved);
        return objectAndMessage;
    }

    @Override
    public ObjectAndMessage deleteVendorById(int id) {
        ObjectAndMessage objectAndMessage=new ObjectAndMessage ();
        Vendor vendor = vendorRepository.findById(id);
        vendor.setDeleted(true);
        vendorRepository.save(vendor);
        objectAndMessage.setObject(null);
        objectAndMessage.setMessage("Vendor was deleted!");
        return objectAndMessage;

    }
}
