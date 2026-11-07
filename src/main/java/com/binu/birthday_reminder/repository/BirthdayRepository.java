package com.binu.birthday_reminder.repository;

import com.binu.birthday_reminder.entity.Birthday;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BirthdayRepository extends JpaRepository<Birthday, Long> {
    boolean existsByNameIgnoreCaseAndDateOfBirth(
            String name,
            LocalDate dateOfBirth);

    @Query("""
                SELECT b FROM Birthday b
                WHERE MONTH(b.dateOfBirth) = :month
                AND DAY(b.dateOfBirth) = :day
            """)
    List<Birthday> findBirthdaysByMonthAndDay(
            @Param("month") int month,
            @Param("day") int day);
}
