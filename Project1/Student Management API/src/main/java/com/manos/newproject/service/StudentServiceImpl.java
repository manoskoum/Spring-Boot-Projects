package com.manos.newproject.service;

import com.manos.newproject.exceptions.ResourceNotFoundException;
import com.manos.newproject.model.Student;
import com.manos.newproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
    }

    public void addStudent(Student student) {
        Optional <Student> addedstudent=studentRepository.findByFirstnameAndLastname(student.getFirstname(),student.getLastname());
        if (addedstudent.isPresent()) {
            throw new IllegalArgumentException("Student already exists with this name");
        }


        studentRepository.save(student);
    }

    public Student updateStudent(Student student,Long id) {
        Student saveStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));

        saveStudent.setFirstname(student.getFirstname());
        saveStudent.setLastname(student.getLastname());
        saveStudent.setAge(student.getAge());

        return studentRepository.save(saveStudent);
    }

    public String deleteStudent(Long id){
        Student findstudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));

        studentRepository.delete(findstudent);
        return "Student with Id :" + id + " deleted successfully";
    }
}
