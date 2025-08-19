package com.school.entity.service;

import com.school.entity.dto.ProfessorsDTO;
import com.school.entity.dto.StudentDTO;
import jakarta.validation.constraints.Positive;

public interface EnrollService {
    StudentDTO enrollStudentInClass(Long studentId,Long classId);

    StudentDTO unenrollStudentFromClass(Long studentId, Long classId);

    ProfessorsDTO assignProfessorToClass( Long professorId,  Long classId, boolean force);

    void unassignProfessorFromClass( Long professorId,  Long classId);
}
