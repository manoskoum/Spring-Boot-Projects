package com.school.entity.service;

import com.school.entity.dto.ClassesDTO;
import com.school.entity.dto.StudentDTO;
import com.school.entity.model.Classes;
import com.school.entity.model.Students;
import com.school.entity.repository.ClassesRepository;
import com.school.entity.repository.StudentsRepository;
import com.school.entity.util.StudentDtoMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {


    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private ClassesRepository classesRepository;

    @Autowired
    private StudentDtoMapper studentDtoMapper;

    public List<StudentDTO> getAllStudents() {
        return studentDtoMapper.toDtoList(studentsRepository.findAll());
    }

    public StudentDTO getStudentById(Long studentId) {
        return studentsRepository.findById(studentId)
                .map(studentDtoMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Student with: " + studentId + " not found"));
    }

    public StudentDTO addStudent(Students student) {
        if (student.getClasses() != null && !student.getClasses().isEmpty()) {
            List<Classes> fetchedClasses = student.getClasses().stream()
                    .filter(Objects::nonNull)
                    .map(c -> {
                        if (c.getClassesId() == null) {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Class id is required");
                        }
                        return classesRepository.findById(c.getClassesId())
                                .orElseThrow(() -> new ResponseStatusException(
                                        HttpStatus.NOT_FOUND, "Class not found with ID: " + c.getClassesId()));
                    })
                    .distinct()
                    .toList();

            student.setClasses(new ArrayList<>(fetchedClasses));
            fetchedClasses.forEach(cls -> cls.getStudents().add(student));
        }

        Students saved = studentsRepository.save(student);
        return studentDtoMapper.toDto(saved);
    }

    @Transactional
    public StudentDTO updateStudent(Long studentId, Students updatedStudent) {
        Students existing = studentsRepository.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

        if (updatedStudent.getStudentName() != null) {
            existing.setStudentName(updatedStudent.getStudentName());
        }

        if (updatedStudent.getClasses() != null && !updatedStudent.getClasses().isEmpty()) {
            Set<Classes> newSet = updatedStudent.getClasses().stream()
                    .filter(Objects::nonNull)
                    .map(c -> {
                        Long id = c.getClassesId();
                        if (id == null) {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Class id is required");
                        }
                        return classesRepository.findById(id)
                                .orElseThrow(() -> new ResponseStatusException(
                                        HttpStatus.NOT_FOUND, "Class not found with ID: " + id));
                    })
                    .collect(Collectors.toCollection(LinkedHashSet::new));

            for (Classes cls : new ArrayList<>(existing.getClasses())) {
                if (!newSet.contains(cls)) {
                    cls.getStudents().remove(existing);
                }
            }

            for (Classes cls : newSet) {
                if (!existing.getClasses().contains(cls)) {
                    cls.getStudents().add(existing);
                }
            }
            existing.setClasses(new ArrayList<>(newSet));
        }


        Students saved = studentsRepository.save(existing);


        StudentDTO dto = new StudentDTO();
        dto.setStudentId(saved.getStudentId());
        dto.setStudentName(saved.getStudentName());

        List<StudentDTO.ClassDTO> classDTOs =
                saved.getClasses() == null ? List.of()
                        : saved.getClasses().stream()
                        .filter(Objects::nonNull)
                        .map(cls -> {
                            StudentDTO.ClassDTO cd = new StudentDTO.ClassDTO();
                            cd.setClassesId(cls.getClassesId());
                            cd.setClassName(cls.getClassName());
                            return cd;
                        })
                        .toList();

        dto.setClasses(classDTOs);
        return dto;
    }


    @Transactional
    public void deleteStudent(Long id) {
        Students student = studentsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Student with id: " + id + " not found"));

        for (Classes cls : new ArrayList<>(student.getClasses())) {
            cls.getStudents().remove(student);
        }

        student.getClasses().clear();

        studentsRepository.delete(student);
    }
}