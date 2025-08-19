package com.school.entity.repository;

import com.school.entity.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentsRepository extends JpaRepository<Students,Long> {
    @Query("""
           select s from Students s
           left join fetch s.classes
           where s.studentId = :id
           """)
    Optional<Students> findByIdWithClasses(@Param("id") Long id);
}
