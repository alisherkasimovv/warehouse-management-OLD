package uz.wh.db.dao;

import org.springframework.stereotype.Service;
import uz.wh.collections.VendorAndMessage;
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
    public VendorAndMessage saveEditVendor(Vendor vendor) {
        Vendor saved;
        VendorAndMessage vendorAndMessage = new VendorAndMessage();
        Vendor temp = venderRepository.findById(vendor.getId());
        if(temp != null){
            temp.setDeleted(false);
            temp.setName(vendor.getName());
            temp.setPhone(vendor.getPhone());
            temp.setAddres(vendor.getAddres());
            saved = venderRepository.save(temp);
            vendorAndMessage.setMessage("Vendor has been updated!");
        }else{
            saved = venderRepository.save(vendor);
            vendorAndMessage.setMessage("Vendor has been created!");
        }
        vendorAndMessage.setVendor(saved);
        return  vendorAndMessage;
    }

    @Override
    public String deleteVendorById(int id) {
        Vendor vendor = venderRepository.findById(id);
        vendor.setDeleted(true);
        venderRepository.delete(vendor);
        return "Cable was deleted!";

    }
}
