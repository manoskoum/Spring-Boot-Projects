package com.school.entity.service;

import com.school.entity.dto.ClassesDTO;
import com.school.entity.model.Classes;
import com.school.entity.model.Professors;
import com.school.entity.model.Students;
import com.school.entity.repository.ClassesRepository;
import com.school.entity.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    @Autowired
    private ClassesRepository classesRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public List<ClassesDTO> getAllClasses() {
        return classesRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Classes addClass(Classes classes) {
        // Αν υπάρχει καθηγητής, τον βρίσκεις. Αν όχι, τον αφήνεις null.
        if (classes.getProfessor() != null && classes.getProfessor().getProfessorId() != null) {
            Long professorId = classes.getProfessor().getProfessorId();

            Professors professor = professorRepository.findById(professorId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor not found"));

            classes.setProfessor(professor);
        } else {
            classes.setProfessor(null); // explicitly set null
        }

        // Το ίδιο μπορείς να κάνεις και για students αν χρειάζεται
        return classesRepository.save(classes);
    }

    public Classes deleteClass(Long classId) {
        Classes classes = classesRepository.findById(classId)
                .orElseThrow(() -> new RuntimeException("Class not found"));
        classesRepository.delete(classes);
        return classes;
    }

    public ClassesDTO mapToDTO(Classes entity) {
        String professorName = entity.getProfessor() != null
                ? entity.getProfessor().getProfessorName()
                : null;

        List<String> studentNames = entity.getStudents() != null
                ? entity.getStudents().stream()
                .map(Students::getStudentName)
                .collect(Collectors.toList())
                : new ArrayList<>();

        return new ClassesDTO(
                entity.getClassesId(),
                entity.getClassName(),
                professorName,
                studentNames
        );
    }
}