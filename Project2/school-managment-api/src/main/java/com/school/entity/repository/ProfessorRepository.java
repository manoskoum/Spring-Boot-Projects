package com.school.entity.repository;

import com.school.entity.model.Professors;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professors,Long> {
}
