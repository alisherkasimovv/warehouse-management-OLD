package uz.wh.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.wh.db.entities.documentation.Outgo;

import java.time.LocalDateTime;
import java.util.List;

public interface OutgoRepository extends JpaRepository<Outgo,Integer> {
    List<Outgo> findAll();

    Outgo findById(int id);

    Outgo findByDocumentNo(String documnetNo);


    Outgo findByOrderDate(LocalDateTime date);

    Outgo findByCustomerId(int id);
}
