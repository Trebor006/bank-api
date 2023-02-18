package com.test.bankapi.repository;

import com.test.bankapi.entity.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface MovementRepository extends JpaRepository<Movement, Long> {

    @Query("SELECT m FROM Movement m WHERE m.date BETWEEN ?1 AND ?2 ORDER BY m.id")
    List<Movement> findByDates(LocalDateTime startDate, LocalDateTime endDate);

}
