package uz.wh.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.wh.db.entities.Vendor;

import java.util.List;

public interface VendorRepository extends JpaRepository<Vendor,Integer> {
    List<Vendor> findAll();
    Vendor findById(int id);
}
