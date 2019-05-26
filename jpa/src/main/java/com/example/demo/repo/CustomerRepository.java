package com.example.demo.repo;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByEmail(String email);

    @Query("from Customer where regDate between :start and :end")
    List<Customer> findByRegDateRange(@Param("start") LocalDate start, @Param("end") LocalDate end);
}
