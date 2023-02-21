package com.test.bankapi.repository;

import com.test.bankapi.entity.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MovementRepository extends JpaRepository<Movement, Long> {

    @Query("SELECT m FROM Movement m WHERE m.date BETWEEN ?1 AND ?2 ORDER BY m.id")
    List<Movement> findByDates(LocalDateTime startDate, LocalDateTime endDate);


    @Query("SELECT m.date as date, c.person.name as customerName, a.number as accountNumber, " +
            "a.type as accountType, a.initialBalance as accountInitialBalance, " +
            "a.status as accountStatus, m.value as movementValue, m.balance as movementBalance " +
            "FROM Movement m " +
            "INNER JOIN m.account a " +
            "INNER JOIN a.customer c " +
            "WHERE m.date BETWEEN :startDate AND :endDate " +
            "AND c.person.name = :customerName")
    List<Object[]> getMovementsByCustomerAndDateRange(@Param("customerName") String customerName,
                                                      @Param("startDate") LocalDateTime startDate,
                                                      @Param("endDate") LocalDateTime endDate);

}
