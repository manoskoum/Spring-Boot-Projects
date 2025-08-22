package com.manos.newproject.service;

import com.manos.newproject.dto.StudentRequestDTO;
import com.manos.newproject.dto.StudentResponseDTO;
import com.manos.newproject.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Page<StudentResponseDTO> getAllStudents(Pageable pageable);

    StudentResponseDTO getStudentById(Long id);

    Page<StudentResponseDTO> searchStudentsByLastname(String lastName, Pageable pageable);

    void addStudent(StudentRequestDTO studentRequestDTO);

    StudentResponseDTO updateStudent(StudentRequestDTO studentRequestDTO,Long id);

    String deleteStudent(Long id);
}
