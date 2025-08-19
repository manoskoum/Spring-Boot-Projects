package com.school.entity.controller;

import com.school.entity.dto.ProfessorsDTO;
import com.school.entity.dto.StudentDTO;
import com.school.entity.service.EnrollService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("enroll")
@Transactional
public class EnrollController {

    @Autowired
    private EnrollService enrollService;

    @PostMapping("/{studentId}/classes/{classId}")
    public ResponseEntity<StudentDTO> enroll(@PathVariable Long studentId, @PathVariable Long classId) {
        StudentDTO dto = enrollService.enrollStudentInClass(studentId, classId);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/professors/{professorId}/classes/{classId}")
    public ResponseEntity<ProfessorsDTO> assign(@PathVariable @Positive Long professorId,
                                                @PathVariable("classId") @Positive Long classId,
                                                @RequestParam(name = "force", defaultValue = "false") boolean force) {
        ProfessorsDTO dto = enrollService.assignProfessorToClass(professorId, classId, force);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @DeleteMapping("/{studentId}/classes/{classId}")
    public ResponseEntity<StudentDTO> unEnroll(@PathVariable Long studentId, @PathVariable Long classId) {
        StudentDTO dto = enrollService.unenrollStudentFromClass(studentId, classId);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/professors/{professorId}/classes/{classId}")
    public ResponseEntity<Void> unassign(@PathVariable @Positive Long professorId,
                                         @PathVariable("classId") @Positive Long classId) {
        enrollService.unassignProfessorFromClass(professorId, classId);
        return ResponseEntity.noContent().build();
    }
}
