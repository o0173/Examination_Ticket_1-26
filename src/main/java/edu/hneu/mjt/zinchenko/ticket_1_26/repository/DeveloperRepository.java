package edu.hneu.mjt.zinchenko.ticket_1_26.repository;

import edu.hneu.mjt.zinchenko.ticket_1_26.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {

}
