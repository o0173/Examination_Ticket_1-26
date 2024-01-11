package edu.hneu.mjt.zinchenko.ticket_1_26.repository;

import edu.hneu.mjt.zinchenko.ticket_1_26.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    @Query("SELECT d.name, COUNT(sp.id) FROM Developer d LEFT JOIN SoftwareProduct sp ON d.id = sp.developer.id GROUP BY d.name")
    List<Object[]> findDeveloperProductCount();
}
