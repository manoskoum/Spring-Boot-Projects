package com.school.entity.service;

import com.school.entity.dto.ProfessorsDTO;
import com.school.entity.dto.StudentDTO;
import com.school.entity.model.Classes;
import com.school.entity.model.Professors;
import com.school.entity.model.Students;
import com.school.entity.repository.ClassesRepository;
import com.school.entity.repository.ProfessorRepository;
import com.school.entity.repository.StudentsRepository;
import com.school.entity.util.ProfessorDtoMapper;
import com.school.entity.util.StudentDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class EnrollServiceImpl implements EnrollService {

    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private ClassesRepository classesRepository;

    @Autowired
    private StudentDtoMapper studentDtoMapper;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private ProfessorDtoMapper professorDtoMapper;

    @Override
    public StudentDTO enrollStudentInClass(Long studentId, Long classId) {
        Students student = studentsRepository.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

        Classes cls = classesRepository.findById(classId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Class not found"));

        if (student.getClasses() == null) {
            student.setClasses(new ArrayList<>());
        }

        boolean already = student.getClasses().stream()
                .anyMatch(c -> Objects.equals(c.getClassesId(), classId));
        if (already) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Student " + studentId + " is already enrolled in class " + classId);
        }

        student.getClasses().add(cls);
        cls.getStudents().add(student);

        Students saved = studentsRepository.save(student);

        return studentDtoMapper.toDto(saved);
    }


    @Override
    public StudentDTO unenrollStudentFromClass(Long studentId, Long classId) {
        Students student = studentsRepository.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

        Classes cls = classesRepository.findById(classId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Class not found"));

        if (student.getClasses() != null) {
            student.getClasses().removeIf(c -> Objects.equals(c.getClassesId(), classId));
            cls.getStudents().removeIf(s -> Objects.equals(s.getStudentId(), studentId));
        }

        Students saved = studentsRepository.save(student);
        return studentDtoMapper.toDto(saved);
    }

    @Override
    public ProfessorsDTO assignProfessorToClass(Long professorId, Long classId, boolean force) {
        Professors professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor not found"));

        Classes cls = classesRepository.findById(classId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Class not found"));

        Professors existing = cls.getProfessor();
        if (existing != null && !Objects.equals(existing.getProfessorId(), professorId)) {
            if (!force) {
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                        "Class '" + cls.getClassName() + "' is already assigned to a professor.");
            } else {

                if (existing.getClasses() != null) {
                    existing.getClasses().removeIf(c -> Objects.equals(c.getClassesId(), classId));
                }
            }
        }

        if (existing != null && Objects.equals(existing.getProfessorId(), professorId)) {
            return professorDtoMapper.toDto(professor);
        }

        cls.setProfessor(professor);
        if (professor.getClasses() == null) {
            professor.setClasses(new ArrayList<>());
        }
        boolean alreadyInList = professor.getClasses().stream()
                .anyMatch(c -> Objects.equals(c.getClassesId(), classId));
        if (!alreadyInList) {
            professor.getClasses().add(cls);
        }
        classesRepository.save(cls);

        return professorDtoMapper.toDto(professor);
    }



    @Override
    public void unassignProfessorFromClass(Long professorId, Long classId) {

        Professors professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor not found"));

        Classes cls = classesRepository.findById(classId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Class not found"));

        if (cls.getProfessor() == null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Class has no professor assigned.");
        }
        if (!Objects.equals(cls.getProfessor().getProfessorId(), professorId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Class is assigned to another professor.");
        }

        cls.setProfessor(null);
        if (professor.getClasses() != null) {
            professor.getClasses().removeIf(c -> Objects.equals(c.getClassesId(), classId));
        }

        classesRepository.save(cls);
    }

    }



