package com.manos.newproject.service;

import com.manos.newproject.dto.StudentRequestDTO;
import com.manos.newproject.dto.StudentResponseDTO;
import com.manos.newproject.exceptions.ResourceNotFoundException;
import com.manos.newproject.model.Student;
import com.manos.newproject.repository.StudentRepository;
import com.manos.newproject.util.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    public Page<StudentResponseDTO> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable)
                .map(studentMapper::toDto);
    }

    public StudentResponseDTO getStudentById(Long id) {
        Student student =studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
        return studentMapper.toDto(student);
    }

    public Page<StudentResponseDTO> searchStudentsByLastname(String lastName, Pageable pageable) {
        return studentRepository.findByLastNameContainingIgnoreCase(lastName, pageable)
                .map(studentMapper::toDto);
    }

    public void addStudent(StudentRequestDTO studentRequestDTO) {
        Optional <Student> addedStudent=studentRepository
                .findByFirstNameAndLastName(studentRequestDTO.getFirstName(),studentRequestDTO.getLastName());
        if (addedStudent.isPresent()) {
            throw new IllegalArgumentException("Student already exists with this name");
        }
        studentRepository.save(studentMapper.toEntity(studentRequestDTO));
    }

    public StudentResponseDTO updateStudent(StudentRequestDTO studentRequestDTO,Long id) {
        Student saveStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));

        saveStudent.setFirstName(studentRequestDTO.getFirstName());
        saveStudent.setLastName(studentRequestDTO.getLastName());
        saveStudent.setAge(studentRequestDTO.getAge());

        return studentMapper.toDto(studentRepository.save(saveStudent));
    }

    public String deleteStudent(Long id){
        Student findstudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));

        studentRepository.delete(findstudent);
        return "Student with Id :" + id + " deleted successfully";
    }
}
