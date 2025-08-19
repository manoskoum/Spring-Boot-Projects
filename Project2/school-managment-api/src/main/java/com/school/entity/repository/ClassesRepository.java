package com.school.entity.repository;

import com.school.entity.model.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClassesRepository extends JpaRepository<Classes, Long> {
    @Query("SELECT c FROM Classes c JOIN c.students s WHERE s.studentId = :studentId")
    List<Classes> findByStudentId(@Param("studentId") Long studentId);
}
