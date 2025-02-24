package com.manos.newproject.service;

import com.manos.newproject.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<Student> getAllStudents();

    Student getStudentById(Long id);

    void addStudent(Student student);

    Student updateStudent(Student student,Long id);

    String deleteStudent(Long id);
}
