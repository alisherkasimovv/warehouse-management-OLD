package uz.wh.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sun.management.jdp.JdpPacket;
import uz.wh.db.entities.Warehouse;

import java.util.List;

public interface WarehouseRepository extends JpaRepository<Warehouse,Integer> {

    Warehouse findById(int id);
    List<Warehouse> findAll();

}
