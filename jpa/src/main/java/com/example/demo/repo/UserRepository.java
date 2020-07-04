package com.example.demo.repo;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("from User where created between :start and :end")
    List<User> findByDateRange(@Param("start") LocalDate start, @Param("end") LocalDate end);
}
