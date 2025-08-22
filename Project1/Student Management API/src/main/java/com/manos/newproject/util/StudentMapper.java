package com.manos.newproject.util;

import com.manos.newproject.dto.StudentRequestDTO;
import com.manos.newproject.dto.StudentResponseDTO;
import com.manos.newproject.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student toEntity(StudentRequestDTO dto) {
        Student student = new Student();
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setAge(dto.getAge());
        return student;
    }

    public StudentResponseDTO toDto(Student student) {
        return new StudentResponseDTO(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getAge()
        );
    }

}