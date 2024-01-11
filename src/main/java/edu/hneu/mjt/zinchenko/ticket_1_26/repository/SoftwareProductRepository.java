package edu.hneu.mjt.zinchenko.ticket_1_26.repository;

import edu.hneu.mjt.zinchenko.ticket_1_26.entity.SoftwareProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoftwareProductRepository extends JpaRepository<SoftwareProduct, Long> {
    List<SoftwareProduct> findByDeveloperId(Long developerId);
}
